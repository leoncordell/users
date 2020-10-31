package com.dwp.users.service;

import com.dwp.users.model.User;

import java.util.List;

public class DataSetupHelper {
    static double NORTHAMPTON_LATITUDE = 52.2405;
    static double NORTHAMPTON_LONGITUDE = -0.9027;

    double LONDON_LATITUDE = 51.509865;
    static double LONDON_LONGITUDE = -0.118092;


    //52.1386° N, 0.4668° W
    static double BEDFORD_LATITUDE = 52.1386;
    static double BEDFORD_LONGITUDE = -0.4668;

    static public User[] getUsers(){
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
    static public User[] getUsersWithinRange(){
        User[] users = new User[1];
        User a = new User();
        a.setFirst_name("Bedford");
        a.setLatitude(BEDFORD_LATITUDE);
        a.setLongitude(BEDFORD_LONGITUDE);
        users[0]=a;
        return users;
    }
}
