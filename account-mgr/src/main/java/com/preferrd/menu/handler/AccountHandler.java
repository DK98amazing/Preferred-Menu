package com.preferrd.menu.handler;

import static java.time.Duration.ofMillis;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.preferrd.menu.dao.bean.Account;
import com.preferrd.menu.service.AccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * AccountHandler.
 *
 * @author liguoyao
 */
@Component
public class AccountHandler implements PreferredMenuHandler{
	Flux<Long> flux;
	Flux<Account> fluxAccount;
	@Autowired
	private AccountService accountService;

	public AccountHandler() {

		flux = Flux.interval(ofMillis(1000)).onBackpressureDrop().map(this::generateQuotes)
				.flatMapIterable(quotes -> quotes).log("io.spring.workshop.stockquotes").share();

		fluxAccount = Flux.interval(ofMillis(1000)).onBackpressureDrop().map(this::listAccount)
				.flatMapIterable(account -> account).log("search all accounts").share();
	}

	public List<Long> generateQuotes(long interval) {
		List<Long> ret = new ArrayList<Long>();
		ret.add(interval);
		return ret;
	}

	public Set<Account> listAccount(long interval) {
		Set<Account> accounts = accountService.collecteAccount();
		return accounts;
	}

	public Mono<ServerResponse> accounts(ServerRequest request) {
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(fluxAccount, Account.class);
	}

	public Mono<ServerResponse> hello(ServerRequest request) {
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, Long.class);
	}

}
