package com.dwp.users.service;

import mockit.Mock;
import mockit.Mocked;
import mockit.Tested;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class DistanceServiceTest {


    DistanceService distanceService = new DistanceService();

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }



    @Test
     void doCheckDistanceOutside() {
       // london.latitude=51.509865
       // london.longitude=-0.118092f
        ReflectionTestUtils.setField(distanceService,"londonLatitude",51.509865);
        ReflectionTestUtils.setField(distanceService,"londonLongitude",-0.118092f);
        // 52.2405° N, 0.9027° W  Northampton
        double NORTHAMPTON_LATITUDE = 52.2405;
        double NORTHAMPTON_LONGITUDE = -0.9027;

        double LONDON_LATITUDE = 51.509865;
        double LONDON_LONGITUDE = -0.118092;
        double result = distanceService.distFrom( NORTHAMPTON_LATITUDE, NORTHAMPTON_LONGITUDE);
        assertTrue(result > 50);

        //52.1386° N, 0.4668° W
        double BEDFORD_LATITUDE = 52.1386;
        double BEDFORD_LONGITUDE = -0.4668;
        double bedfordDistance = distanceService.distFrom(BEDFORD_LATITUDE, BEDFORD_LONGITUDE);
        assertTrue(bedfordDistance <= 50);

    }
}