package com.dwp.users.service;

import com.dwp.users.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Using  https://bpdts-test-app.herokuapp.com  endpoints see
 */

@Slf4j
@Service
public class HeroGatewayService {

    @Value("${london.users.url}")
    private String londonUsersUrl;


    @Value("${all.users.url}")
    private String allUsersUrl;

    @Value("${radius.miles}")
    private Integer radiusToApply;


    @Autowired
    DistanceService distanceService;

    @Autowired
    HeroGateWayClient heroGateWayClient;


    /**
     * Returns people who are
     * listed as either living in London, or whose current coordinates are within 50 miles of London.
     *
     * @return List of Users
     */
    public List<User> retrieveUsers() {
        Flux<User> allUsersFlux = heroGateWayClient.getClient(allUsersUrl);
        Flux<User> cityusersFlux = heroGateWayClient.getClient(londonUsersUrl);
        List<User> usersWithinRadius = allUsersFlux.collectList().block().stream().filter(u -> distanceService.distFrom(u.getLatitude(), u.getLongitude()
                ) <= radiusToApply).collect(toList());
        Set<User> combinedCityAndRadiusUsers = cityusersFlux.collectList().block().stream().collect(toSet());

        combinedCityAndRadiusUsers.addAll(usersWithinRadius);
        return new ArrayList<>(combinedCityAndRadiusUsers);
    }

    public List<User> retrieveCityUsers() {
        Flux<User> cityusersFlux = heroGateWayClient.getClient(londonUsersUrl);
        return cityusersFlux.collectList().block().stream().collect(toList());
    }

    public List<User> retrieveWithinRangeUsersOnly() {
        Flux<User> allUsersFlux = heroGateWayClient.getClient(allUsersUrl);

        List<User> block = allUsersFlux.collectList().block();

        List<User> usersWithinRadius = block.stream().filter(u -> distanceService.distFrom(u.getLatitude(), u.getLongitude()) <= radiusToApply).collect(toList());

        return new ArrayList<>(usersWithinRadius);
    }

}
