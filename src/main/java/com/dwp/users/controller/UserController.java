package com.dwp.users.controller;

import com.dwp.users.model.User;
import com.dwp.users.model.UserError;
import com.dwp.users.service.HeroGatewayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "UserController")
public class UserController {
    @Autowired
    HeroGatewayService heroGatewayService;

    /**
     * @return All people within London plus all people within 50 miles of London
     */
    @ApiOperation(value = "Get list of all people  in London and within 50 miles of London", response = Iterable.class, tags = "GetPeopleLondonAndCatchment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "not available!")})
    @GetMapping("/city/London/users/all")
    @ResponseBody
    @ExceptionHandler(UserError.class)
    public List<User> getAllUsersInCityAndWithinCatchment() {
        return heroGatewayService.retrieveUsers();
    }

    /**
     * @return All people from London
     */

    @ApiOperation(value = "Get list of people  in London only ", response = Iterable.class, tags = "GetPeopleInLondon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "not available!")})
    @GetMapping("/city/London/users")
    @ResponseBody
    public List<User> getCityUsers() {
        return heroGatewayService.retrieveCityUsers();
    }

    /**
     * @return All people who are within 50 miles of London , but are not actually within London
     */
    @ApiOperation(value = "Get list of people  within 50 miles of London (But not in London) ", response = Iterable.class, tags = "GetPeopleIn50MileOutsideLondon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "not available!")})
    @GetMapping("/city/London/users/catchment")
    @ResponseBody
    public List<User> getCatchmentUsers() {
        return heroGatewayService.retrieveWithinRangeUsersOnly();
    }

}
