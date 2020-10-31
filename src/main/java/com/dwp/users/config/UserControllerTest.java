package com.dwp.users.config;

import com.dwp.users.UsersApplication;
import com.dwp.users.controller.UserController;
import com.dwp.users.model.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest(classes = UsersApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration
public class UserControllerTest {
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private UserController userController;
//
//    String pathToTest = "/city/London";
//
////   @Test
////    public  void getSimple(){
////        String result = this.restTemplate.getForObject("http://localhost:" + port + pathToTest,
////                String.class);
////        assertTrue(result.contains("Lon"));
////    }
//
//    @Test
//    public void londonOnlyUsers() {
//        ResponseEntity<List<User>> result = this.restTemplate.exchange(
//                "http://localhost:" + port + "base/city/London/users",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<User>>() {
//                });
//
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    public void londonCatchmentUsersCombined() {
//        ResponseEntity<List<User>> result = this.restTemplate.exchange(
//                "http://localhost:" + port + "base/city/London/users/all",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<User>>() {
//                });
//
//        Assertions.assertNotNull(result);
//    }
//
//    @Test
//    public void londonCatchmentUsersOnly() {
//        ResponseEntity<List<User>> result = this.restTemplate.exchange(
//                "http://localhost:" + port + "base/city/London/users/catchment",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<User>>() {
//                });
//
//        Assertions.assertNotNull(result);
//    }

}