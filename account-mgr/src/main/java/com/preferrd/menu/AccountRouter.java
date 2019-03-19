package com.preferrd.menu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * AccountRouter.
 *
 * @author liguoyao
 */
@Configuration public class AccountRouter {

    @Bean public RouterFunction<ServerResponse> routerFunction(AccountHandler accountHandler) {
        return RouterFunctions
            .route(GET("/helloReactive").and(accept(MediaType.APPLICATION_STREAM_JSON)), accountHandler::hello)
            .andRoute(GET("/getUser/{accountId}").and(accept(MediaType.APPLICATION_JSON)), accountHandler::getUsers)
            .andRoute(POST("/addUser").and(accept(MediaType.APPLICATION_JSON)), accountHandler::putUsers);
    }
}
