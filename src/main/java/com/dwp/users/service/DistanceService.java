package com.dwp.users.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {

    @Value("${london.latitude}")
     private double londonLatitude;

    @Value("${london.longitude}")
     private double londonLongitude;


    /**
     * Distance between  two points based on their latitudes and longitudes
     *
     * @param lat1 - The Latitude of the user
     * @param lng1 - Longitude of the user
     *
     * @return distance in miles
     */

    public  double distFrom(double lat1, double lng1) {
        double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
        double dLat = Math.toRadians(londonLatitude - lat1);
        double dLng = Math.toRadians(londonLongitude - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(londonLatitude));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }
}
