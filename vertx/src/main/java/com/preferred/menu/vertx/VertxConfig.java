package com.preferred.menu.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class VertxConfig {

    private CuratorFramework curatorFramework;
    private JsonObject zkConfig = new JsonObject();

    @PostConstruct
    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        curatorFramework =
                CuratorFrameworkFactory.newClient("127.0.0.1:2181", new RetryOneTime(1000));
        curatorFramework.start();
        try {
            curatorFramework.blockUntilConnected();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        zkConfig.put("zookeeperHosts", "127.0.0.1");
//        zkConfig.put("rootPath", "io.vertx");
//        zkConfig.put("retry", new JsonObject()
//                .put("initialSleepTime", 3000)
//                .put("maxTimes", 3).put("intervalTimes", 1000));
    }

    @Bean(value = "singleVertx")
    public Vertx vertxSingle() {
        VertxOptions vertxOptions = new VertxOptions().setWorkerPoolSize(8);
        Vertx vertx = Vertx.vertx(vertxOptions);
        vertx.createSharedWorkerExecutor("my_workPool");
        return vertx;
    }

//    @Bean(value = "clusterVertx")
//    public Vertx vertxCluster() {
//        SettableListenableFuture<Vertx> settableListenableFuture = new SettableListenableFuture<>();
//        ClusterManager mgr = new ZookeeperClusterManager(zkConfig);
//        EventBusOptions eventBusOptions = new EventBusOptions().setClustered(true);
//        VertxOptions vertxOptions = new VertxOptions().setClusterManager(mgr).setEventBusOptions(eventBusOptions);
//        Vertx.clusteredVertx(vertxOptions, vertxAsyncResult -> {
//            if (vertxAsyncResult.succeeded()) {
//                settableListenableFuture.set(vertxAsyncResult.result());
//            } else {
//                settableListenableFuture.setException(vertxAsyncResult.cause());
//            }
//        });
//        try {
//            return settableListenableFuture.get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
