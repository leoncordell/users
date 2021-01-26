package com.dwp.users.service;

import com.dwp.users.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class HeroGateWayClient {

    @Value("${gateway.timout}")
    private int timeout;

    private static final Logger LOGGER = LoggerFactory.getLogger("HeroGateWayClient.class");


    public Flux<User> getClient(String url) {

        return WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(User.class)
                .timeout(Duration.ofMillis(timeout))
                .doOnError(error -> LOGGER.error("Error signal detected", error));
    }
}
