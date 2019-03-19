package com.preferrd.menu;

import com.preferrd.menu.model.Account;
import com.preferrd.menu.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofMillis;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * AccountHandler.
 *
 * @author liguoyao
 */
@Component
public class AccountHandler {
	@Autowired
	private AccountService accountService;
	Flux<Long> flux;

	public AccountHandler() {
		flux = Flux.interval(ofMillis(1000)).onBackpressureDrop()
				.map(this::generateQuotes).flatMapIterable(quotes -> quotes)
				.log("io.spring.workshop.stockquotes").share();
	}

	public List<Long> generateQuotes(long interval) {
		List<Long> ret = new ArrayList<Long>();
		ret.add(interval);
		return ret;
	}

	public Mono<ServerResponse> hello(ServerRequest request) {
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux,
				Long.class);
	}

	public Mono<ServerResponse> getUsers(ServerRequest request) {
		String id;
		try {
			// 获取url的属性
			id = request.pathVariable("accountId");
		} catch (IllegalArgumentException e) {
			id = "1";
		}
		System.out.println("id: ---------" + id);
		Account account = accountService.getNameById(id);
		return ok().contentType(MediaType.APPLICATION_JSON).body(
				BodyInserters.fromObject(null == account ? "data not exist"
						: account));
	}

	public Mono<ServerResponse> putUsers(ServerRequest request) {

		Mono<Account> accountMono = request.bodyToMono(Account.class);
		Mono<Account> ret = accountMono.doOnSuccess(accountor -> accountService
				.addUser(accountor));
		return ok().contentType(APPLICATION_JSON).body(ret, Account.class);
	}
}
