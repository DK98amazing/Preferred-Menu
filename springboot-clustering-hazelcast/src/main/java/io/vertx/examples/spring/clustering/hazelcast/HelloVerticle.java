package io.vertx.examples.spring.clustering.hazelcast;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.impl.VertxImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Thomas Segismont
 */
public class HelloVerticle extends AbstractVerticle {

    public static final String HELLO = "hello";
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        log.info("hello start...");
        vertx.eventBus().<String>consumer(HELLO, message -> {
            log.info("hello 收到" + message.body());
            VertxImpl vertxImp = (VertxImpl) vertx;
            String id = vertxImp.getClusterManager().getNodeID();
            message.reply(id + " replay" + message.body());
        });
//      .completionHandler(startFuture);
    }
}
