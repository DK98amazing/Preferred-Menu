package com.preferred.menu.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.impl.CookieImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class VerticleHttp2 extends AbstractVerticle {

    @Autowired
    @Qualifier(value = "singleVertx")
    private Vertx vertx;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(vertx);
        router.route("/testHttp").method(HttpMethod.GET).handler(event -> {
            System.err.println(event.cookies());
            if (event.cookieCount()==0) {
                event.addCookie(new CookieImpl("mycookie", "mycookieValue"));
            }
            HttpServerResponse response = event.response();
            response.setChunked(true);
            response.write("route1\n");
            event.next();
        });
        router.route("/testHttp").method(HttpMethod.GET).handler(event -> {
            System.err.println(event.cookies());
            HttpServerResponse response = event.response();
            response.write("route2\n");
            response.end();
        });
        router.route("/testHttp2").method(HttpMethod.GET).handler(event -> {
            System.err.println(event.cookies());
            HttpServerResponse response = event.response();
            response.write("route22\n");
            response.end();
        });
        Route route = router.get("/testHttp");
        route.failureHandler(event -> {
            System.err.println(event.failure());
        });
        HttpServerOptions httpServerOptions = new HttpServerOptions().setHost("127.0.0.1")
                .setPort(9996).setTcpNoDelay(true);
        HttpServer server = vertx.createHttpServer(httpServerOptions).requestHandler(router);
        server.listen(9997);
        startPromise.complete();
    }
}
