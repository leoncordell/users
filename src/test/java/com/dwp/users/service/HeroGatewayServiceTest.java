package com.dwp.users.service;

import com.dwp.users.model.User;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeroGatewayServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(HeroGatewayService.class);


    HeroGatewayService heroGatewayService = new HeroGatewayService();


    DistanceService distanceService;

    @Mocked
    HeroGateWayClient heroGateWayClient;

    @Mocked
    private ExchangeFunction exchangeFunction;

    Flux<User> cityusersFlux;
    Flux<User> allUsersFlux;

    String CITY_USERS_URL = "";
    String ALL_USERS_URL = "";

    User[] users =new User[2];
    double NORTHAMPTON_LATITUDE = 52.2405;
    double NORTHAMPTON_LONGITUDE = -0.9027;

    double LONDON_LATITUDE = 51.509865;
    double LONDON_LONGITUDE = -0.118092;


    //52.1386° N, 0.4668° W
    double BEDFORD_LATITUDE = 52.1386;
    double BEDFORD_LONGITUDE = -0.4668;

    @BeforeEach
    void init() {
        users = getUsers();
        heroGatewayService.heroGateWayClient = heroGateWayClient;
        DistanceService distanceService = new DistanceService();
        heroGatewayService.distanceService = distanceService;
        ReflectionTestUtils.setField(distanceService,"londonLatitude",51.509865);
        ReflectionTestUtils.setField(distanceService,"londonLongitude",-0.118092f);
        ReflectionTestUtils.setField(heroGatewayService,"radiusToApply",50);
    }

    public User[] getUsers() {
        User[] users = new User[2];
        User a = new User();
        a.setFirst_name("Bedford");
        a.setLatitude(BEDFORD_LATITUDE);
        a.setLongitude(BEDFORD_LONGITUDE);
        User b = new User();
        b.setFirst_name("Northampton");
        b.setLatitude(NORTHAMPTON_LATITUDE);
        b.setLongitude(NORTHAMPTON_LONGITUDE);
        users[0]=a;
        users[1]=b;
        return users;
    }



    @BeforeEach
    void setUp() {
    }

    @Test
    void retrieveWithinRangeUsersOnly() {
        new Expectations() {{
            Flux<User> fluxy = Flux.create(sink -> {
                        sink.next(users[0]);
                        sink.next(users[1]);
                        sink.complete();
                    });
            heroGateWayClient.getClient(null);
            result = fluxy;
        }};
        List<User> result = heroGatewayService.retrieveWithinRangeUsersOnly();
       assertTrue(result.contains(users[0]));
       assertTrue(!result.contains(users[1]));

    }

    @Test
    void retrieveWithinCityUsersOnly() {
        new Expectations() {{
            Flux<User> fluxy = Flux.create(sink -> {
                sink.next(users[0]);
                sink.next(users[1]);
                sink.complete();
            });
            heroGateWayClient.getClient(null);
            result = fluxy;
        }};
        List<User> result = heroGatewayService.retrieveCityUsers();
        assertTrue(result.contains(users[0]));
        assertTrue(result.contains(users[1]));

    }

    @Test
    void retrieveWithinUsersWithinRangeAndCity() {
        new Expectations() {{
            Flux<User> fluxy = Flux.create(sink -> {
                sink.next(users[0]);
                sink.next(users[1]);
                sink.complete();
            });
            heroGateWayClient.getClient(null);
            result = fluxy;
        }};
        new Expectations() {{
            Flux<User> fluxy = Flux.create(sink -> {
                sink.next(users[0]);
                sink.next(users[1]);
                sink.complete();
            });
            heroGateWayClient.getClient(null);
            result = fluxy;
        }};
        List<User> result = heroGatewayService.retrieveUsers();
        assertTrue(result.contains(users[0]));
        assertTrue(result.contains(users[1]));
        assertEquals(2,result.size());

    }


}