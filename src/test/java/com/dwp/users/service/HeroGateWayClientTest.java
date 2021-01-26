package com.dwp.users.service;

import com.dwp.users.model.User;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;



import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroGateWayClientTest {


    HeroGateWayClient heroGateWayClient = new HeroGateWayClient();

    @Mocked
    private WebClient webClient;

    @BeforeEach
    void setUp() throws IOException {

    }

    @AfterEach
    void tearDown() throws IOException {

    }

    Logger LOGGER = LoggerFactory.getLogger("HeroGateWayClient.class");
    @Test
    void testJMockit() {
        User[] userList = DataSetupHelper.getUsers();
        Flux<User> fluxUsers = Flux.just(userList[0],userList[1]);


        new Expectations() {{

            webClient.get()
                    .uri("abc")
                    .retrieve()
                    .bodyToFlux(User.class)
                    .timeout(Duration.ofMillis(1000));
            result = fluxUsers;
        }};
        ReflectionTestUtils.setField(heroGateWayClient,"timeout",1000);

        Flux<User> result = heroGateWayClient.getClient("abc");
        assertTrue(result.collectList().block().stream().filter(u->u.getFirst_name().equals("Bedford")).findFirst().isPresent());

    }
}