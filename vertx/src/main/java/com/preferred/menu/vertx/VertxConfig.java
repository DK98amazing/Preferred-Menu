package com.preferred.menu.vertx;

import com.hazelcast.config.Config;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.ConfigUtil;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

@Configuration
public class VertxConfig {

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
        Config hazelcastConfig = ConfigUtil.loadConfig();
        hazelcastConfig.getGroupConfig()
                .setName("myClusterName");
        ClusterManager mgr = new HazelcastClusterManager(hazelcastConfig);
        EventBusOptions eventBusOptions = new EventBusOptions().setClustered(true).setClusterPublicHost("127.0.0.1")
                .setClusterPublicPort(6666);
        VertxOptions vertxOptions = new VertxOptions().setClusterManager(mgr).setEventBusOptions(eventBusOptions);
        Vertx.clusteredVertx(vertxOptions, vertxAsyncResult -> {
            if (vertxAsyncResult.succeeded()) {
                settableListenableFuture.set(vertxAsyncResult.result());
            } else {
                settableListenableFuture.setException(new RuntimeException("Vert.x init failed"));
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
