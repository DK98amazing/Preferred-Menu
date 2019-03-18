package com.preferrd.menu.main;


import com.preferrd.menu.main.model.User;
import com.preferrd.menu.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofMillis;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * AccountHandler.
 *
 * @author liguoyao
 */
@Component public class AccountHandler {
    @Autowired private UserService userService;
    Flux<Long> flux;

    public AccountHandler() {
        flux = Flux.interval(ofMillis(1000)).onBackpressureDrop().map(this::generateQuotes)
            .flatMapIterable(quotes -> quotes).log("io.spring.workshop.stockquotes").share();
    }


    public List<Long> generateQuotes(long interval) {
        List<Long> ret = new ArrayList<Long>();
        ret.add(interval);
        return ret;
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, Long.class);
    }

    public Mono<ServerResponse> getUsers(ServerRequest request) {
        String id;
        if (request.queryParam("id").isPresent()) {
            id = request.queryParam("id").get();
        } else {
            id = "1";
        }
        System.out.println("id: ---------" + id);
        User user = userService.getNameById(id);
        return ok().contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(null == user ? "data not exist" : user));
    }
}
