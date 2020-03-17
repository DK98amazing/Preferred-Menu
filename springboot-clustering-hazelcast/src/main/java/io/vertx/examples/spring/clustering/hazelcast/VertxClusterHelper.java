package io.vertx.examples.spring.clustering.hazelcast;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;

/**
 * @Author :liuzhuoren
 * @Description: 该类每个容器只启动一个，且是HA 模式，当第一次启动时发一个广播通知新节点起来了，当HA 切换启动时发现 ndoeid 不一致时 发出原node crash的事件，并自身退出。 该类主要是辅助 集群管理
 * 方便对集群事件需感知 功能的开发， vertx自身的集群事件没有开放出来
 * @Date: 2018/9/16
 */
public class VertxClusterHelper extends AbstractVerticle {

    // 当节点创建时初始化该值，注意 一个容器内只能起一个节点否则改功能异常
    public static String clusterNodeId = "";
    private String lastLeftNodeId;

    public static final String NODEID = "nodeid";
    public static final String NODEADD = "nodeadd";
    public static final String NODELEFT = "nodeleft";
    public static final String PRODUCT_RESTORE = "productrestore";

    public static final String CLUSTER_RUNTIME_EVENT = "cluster.event";
    public static final String CLUSTER_HELPER = "cluster.helper";
    public static final String METHOD_LASTLEFTNODE = "method_lastleftnode";
    public static final String METHOD_UNDEPLOY = "method_undeploy";

    @Override
    public void start() {
        String nodeid = config().getString(NODEID);
        EventBus eventBus = vertx.eventBus();
        MessageConsumer<JsonObject> consumer = eventBus.consumer(CLUSTER_RUNTIME_EVENT);
        consumer.handler(this::clustereventroute);
        MessageConsumer<JsonObject> consumerhelper = eventBus.consumer(CLUSTER_HELPER);
        consumerhelper.handler(this::route);
        System.out.println("执行了start方法 nodeId：" + nodeid + " clusterNodeId:" + VertxClusterHelper.clusterNodeId);
        // 广播通知集群事件
        if (nodeid.equals(VertxClusterHelper.clusterNodeId)) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.put(NODEID, nodeid);
            DeliveryOptions deliveryOptions = new DeliveryOptions();
            deliveryOptions.addHeader("method", NODEADD);
            vertx.eventBus().<JsonObject>publish(CLUSTER_RUNTIME_EVENT, jsonObject, deliveryOptions);
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.put(NODEID, nodeid);
            DeliveryOptions deliveryOptions = new DeliveryOptions();
            deliveryOptions.addHeader("method", NODELEFT);
            vertx.eventBus().<JsonObject>publish(CLUSTER_RUNTIME_EVENT, jsonObject, deliveryOptions);
            vertx.undeploy(this.deploymentID());
        }
    }


    @Override
    public void stop() throws Exception {
        System.out.println(this.deploymentID() + "停止了");
        super.stop();
    }

    /***
     * @Description 方法路由器，从Message的hearder方法里取method的值，分发给对应方法。
     * @Params [stringMessage]
     * @Returns void
     */
    public void clustereventroute(Message<JsonObject> jsonMessage) {
        MultiMap map = jsonMessage.headers();
        String state = map.get("method");
        JsonObject data = jsonMessage.body();
        String nodeid = data.getString(VertxClusterHelper.NODEID);
        System.out.println("VertxClusterHelper Deployment " + state + " :" + nodeid);
        if (nodeid.equals(VertxClusterHelper.clusterNodeId)) {
            return;
        }
        switch (state) {
            case VertxClusterHelper.NODEADD:
                System.out.println("VertxClusterHelper NODEADD");
                break;
            case VertxClusterHelper.NODELEFT:
                System.out.println("VertxClusterHelper NODELEFT");
                break;
            default:
                break;
        }
    }

    /**
     * A node has left
     *
     * @param nodeID The unique ID of the node
     */
    public void nodeLeft(String nodeID) {
        this.lastLeftNodeId = nodeID;
    }

    public void route(Message<JsonObject> jsonMessage) {
        MultiMap map = jsonMessage.headers();
        String method = map.get("method");
        System.out.println("method...................");
        switch (method) {
            case VertxClusterHelper.METHOD_LASTLEFTNODE:
                System.out.println("VertxClusterHelper Deployment setLastLeftNodeId :" + this.lastLeftNodeId);
                break;
            case VertxClusterHelper.METHOD_UNDEPLOY:
                String nodeid = config().getString(NODEID);
                if (nodeid.equals(jsonMessage.body().getString(NODEID))) {
                    System.out.print("VertxClusterHelper Deployment undeploy :" + nodeid);
                    vertx.undeploy(this.context.deploymentID());
                }
                break;
            default:
                break;
        }
    }
}
