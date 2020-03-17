/*
 * Copyright 2017 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.examples.spring.clustering.hazelcast;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Thomas Segismont
 */
public class HttpVerticle extends AbstractVerticle {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    System.out.println("http start...");
    Router router = Router.router(vertx);
    HttpServer server = vertx.createHttpServer();
    server.requestHandler(router::handle).listen(9090, ar -> {
    });
    router.route("/hello").handler(this::hello);
    router.route("/ha").handler(this::ha);
  }

  private void ha(RoutingContext routingContext) {
    String mess = "ha";
    vertx.eventBus().<String>request(HaVerticle.HA_ADD, mess, ar->{
      if (ar.succeeded()) {
        routingContext.response().end(ar.result().body());
      } else {
        routingContext.response().setStatusCode(500).end(ar.cause().getMessage());
      }
    });
  }

  private void hello(RoutingContext routingContext) {
    String mess = "hello";
    log.info("发起HTTP  请求 发送HELLO 事件");
    vertx.eventBus().<String>request(HelloVerticle.HELLO, mess, ar -> {
      if (ar.succeeded()) {
        log.info("得到返回的结果");
        routingContext.response().end(ar.result().body());
      } else {
        routingContext.response().setStatusCode(500).end(ar.cause().getMessage());
      }
    });
  }
}
