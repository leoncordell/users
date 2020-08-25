package com.dwp.users.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HeroGatewayServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(HeroGatewayService.class);


    HeroGatewayService heroGatewayService = new HeroGatewayService();

    @Value("${london.latitude}")
    private double LONDON_LATITUDE;

    @Value("${london.longitude}")
    private double LONDON_LONGITUDE;


    @Test
    public void doCheckDistanceOutside() {
        // 52.2405° N, 0.9027° W  Northampton
        double NORTHAMPTON_LATITUDE = 52.2405;
        double NORTHAMPTON_LONGITUDE = -0.9027;

        double LONDON_LATITUDE = 51.509865;
        double LONDON_LONGITUDE = -0.118092;
        double result = HeroGatewayService.distFrom(LONDON_LATITUDE, LONDON_LONGITUDE, NORTHAMPTON_LATITUDE, NORTHAMPTON_LONGITUDE);
        assertTrue(result > 50);

        //52.1386° N, 0.4668° W
        double BEDFORD_LATITUDE = 52.1386;
        double BEDFORD_LONGITUDE = -0.4668;
        double bedfordDistance = HeroGatewayService.distFrom(LONDON_LATITUDE, LONDON_LONGITUDE, BEDFORD_LATITUDE, BEDFORD_LONGITUDE);
        assertTrue(bedfordDistance <= 50);

    }


}