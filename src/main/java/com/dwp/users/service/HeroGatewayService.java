package com.dwp.users.service;

import com.dwp.users.model.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
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
    private String CITY_USERS_URL;


    @Value("${all.users.url}")
    private String ALL_USERS_URL;

    @Value("${radius.miles}")
    private Integer RADIUS_TO_APPLY;

    @Value("${london.latitude}")
    private double DESTINATION_LATITUDE;

    @Value("${london.longitude}")
    private double DESTINATION_LONGITUDE;

    @Value("${gateway.timout}")
    private int TIMEOUT;

    Logger LOGGER = LoggerFactory.getLogger("HeroGateWayService.class");

    /**
     * Distance between  two points based on their latitudes and longitudes
     *
     * @param lat1 - The Latitude of the user
     * @param lng1 - Longitude of the user
     * @param lat2 - The Latitude of the destination city
     * @param lng2 - Longitude of the destination city
     * @return distance in miles
     */

    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;

    }

    private Flux<User> getClient(String url) {

        return WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(User.class)
                .timeout(Duration.ofMillis(TIMEOUT))
                .doOnError(error -> LOGGER.error("Error signal detected", error));
    }


    /**
     * Returns people who are
     * listed as either living in London, or whose current coordinates are within 50 miles of London.
     *
     * @return List of Users
     */
    public List<User> retrieveUsers() {
        Flux<User> allUsersFlux = getClient(ALL_USERS_URL);
        Flux<User> cityusersFlux = getClient(CITY_USERS_URL);
        List<User> usersWithinRadius = allUsersFlux.collectList().block().stream().filter(u -> distFrom(u.getLatitude(), u.getLongitude(),
                DESTINATION_LATITUDE, DESTINATION_LONGITUDE) <= RADIUS_TO_APPLY).collect(toList());
        Set<User> combinedCityAndRadiusUsers = cityusersFlux.collectList().block().stream().collect(toSet());

        combinedCityAndRadiusUsers.addAll(usersWithinRadius);
        return new ArrayList<>(combinedCityAndRadiusUsers);
    }

    public List<User> retrieveCityUsers() {
        Flux<User> cityusersFlux = getClient(CITY_USERS_URL);
        return cityusersFlux.collectList().block().stream().collect(toList());
    }

    public List<User> retrieveWithinRangeUsersOnly() {
        Flux<User> allUsersFlux = getClient(ALL_USERS_URL);

        List<User> usersWithinRadius = allUsersFlux.collectList().block().stream().filter(u -> distFrom(u.getLatitude(), u.getLongitude(),
                DESTINATION_LATITUDE, DESTINATION_LONGITUDE) <= RADIUS_TO_APPLY).collect(toList());

        return new ArrayList<>(usersWithinRadius);
    }

}
