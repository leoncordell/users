package com.dwp.users.controller;

import com.dwp.users.model.User;
import com.dwp.users.service.DataSetupHelper;
import com.dwp.users.service.HeroGatewayService;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    UserController userController = new UserController();

    @Mocked
    HeroGatewayService heroGatewayService;

    @BeforeEach
    void initialise(){
        userController.heroGatewayService = heroGatewayService;
    }

    @Test
    void getAllUsersInCityAndWithinCatchment() {
        //List<User> users = ;
        new Expectations() {{
            heroGatewayService.retrieveUsers();
            result =DataSetupHelper.getUsers();
        }};
        List<User> result = userController.getCatchmentUsers();
        assertTrue(result.get(0).equals("Bedford"));
    }

    @Test
    void getCityUsers() {
        //List<User> users = ;
        new Expectations() {{
            heroGatewayService.retrieveCityUsers();
            result =DataSetupHelper.getUsers();
        }};
        List<User> result = userController.getCatchmentUsers();
        assertTrue(result.get(0).equals("Bedford"));
    }

    @Test
    void getCatchmentUsers() {
        //List<User> users = ;
        new Expectations() {{
            heroGatewayService.retrieveWithinRangeUsersOnly();
            result =DataSetupHelper.getUsers();
        }};
        List<User> result = userController.getCatchmentUsers();
        assertTrue(result.get(0).equals("Bedford"));
    }


}