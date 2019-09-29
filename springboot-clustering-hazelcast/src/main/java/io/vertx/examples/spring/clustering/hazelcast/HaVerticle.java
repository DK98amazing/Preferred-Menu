package io.vertx.examples.spring.clustering.hazelcast;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.impl.VertxImpl;

public class HaVerticle extends AbstractVerticle {

  public static final String HA_ADD = "haAdd";

  @Override
  public void start() throws Exception {
    System.out.println("HA verticle start...");

    vertx.eventBus().<String>consumer(HA_ADD, message->{
      System.out.println("HA 收到消息" + message.body());
      VertxImpl vertxImp = (VertxImpl) vertx;
      String id = vertxImp.getClusterManager().getNodeID();
      message.reply(id + "replay HA success");
    });
  }
}
