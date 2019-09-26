package com.preferred.menu.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
        ClusterManager mgr = new HazelcastClusterManager();
        VertxOptions vertxOptions = new VertxOptions().setClusterManager(mgr);
        Vertx.clusteredVertx(vertxOptions, vertxAsyncResult -> {
            if (vertxAsyncResult.succeeded()) {
                settableListenableFuture.set(vertxAsyncResult.result());
            } else {
                settableListenableFuture.setException(new RuntimeException("Vert.x init failed"));
            }
        });
        try {
            return settableListenableFuture.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
