package com.preferred.menu.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.concurrent.SettableListenableFuture;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Configuration
public class VertxConfig {

    private CuratorFramework curatorFramework;
    private JsonObject zkConfig = new JsonObject();

    @PostConstruct
    public void init() {
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//        curatorFramework =
//                CuratorFrameworkFactory.builder()
//                        .connectString("127.0.0.1:2181")
//                        .sessionTimeoutMs(60000)
//                        .connectionTimeoutMs(5000)
//                        .retryPolicy(retryPolicy)
//                        /*.aclProvider(new ACLProvider() {
//                            private List<ACL> acl ;
//                            @Override
//                            public List<ACL> getDefaultAcl() {
//                                if(acl ==null) {
//                                    ArrayList<ACL> acl = ZooDefs.Ids.CREATOR_ALL_ACL;
//                                    acl.clear();
//                                    acl.add(new ACL(ZooDefs.Perms.ALL, new Id("auth", "admin:admin")));
//                                    this.acl = acl;
//                                }
//                                return acl;
//                            }
//                            @Override
//                            public List<ACL> getAclForPath(String s) {
//                                return acl;
//                            }
//                        })*/
////                        .namespace(namespace)
//                        .build();
//        curatorFramework.start();
        //        client.getConnectionStateListenable().addListener(new ZookeeperConnectionListener(host,maxRetry,sessionTimeout,connectTimeout,namespace))
        zkConfig.put("zookeeperHosts", "10.0.7.173");
        zkConfig.put("rootPath", "io.vertx");
        zkConfig.put("retry", new JsonObject()
                .put("initialSleepTime", 3000)
                .put("maxTimes", 3).put("intervalTimes", 1000));
    }

    @Bean(value = "singleVertx")
    public Vertx vertxSingle() {
        VertxOptions vertxOptions = new VertxOptions().setWorkerPoolSize(8);
        Vertx vertx = Vertx.vertx(vertxOptions);
        vertx.createSharedWorkerExecutor("my_workPool");
        return vertx;
    }

    @Bean(value = "clusterVertx")
    public Vertx vertxCluster() {
        SettableListenableFuture<Vertx> settableListenableFuture = new SettableListenableFuture<>();
        ClusterManager mgr = new ZookeeperClusterManager(zkConfig);
        EventBusOptions eventBusOptions = new EventBusOptions().setClustered(true);
        VertxOptions vertxOptions = new VertxOptions().setClusterManager(mgr).setEventBusOptions(eventBusOptions);
        Vertx.clusteredVertx(vertxOptions, vertxAsyncResult -> {
            if (vertxAsyncResult.succeeded()) {
                settableListenableFuture.set(vertxAsyncResult.result());
            } else {
                settableListenableFuture.setException(vertxAsyncResult.cause());
            }
        });
        try {
            return settableListenableFuture.get(15, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
