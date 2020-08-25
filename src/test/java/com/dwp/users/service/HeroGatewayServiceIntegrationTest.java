package com.dwp.users.service;

import com.dwp.users.model.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class HeroGatewayServiceIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(HeroGatewayService.class);

    @Autowired
    HeroGatewayService heroGatewayService = new HeroGatewayService();


    @Test
    public void getAllUsers() {
        List<User> usersNonBlocking = heroGatewayService.retrieveUsers();
        assertNotNull(usersNonBlocking);
    }

}