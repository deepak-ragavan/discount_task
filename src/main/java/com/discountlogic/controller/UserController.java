package com.discountlogic.controller;


import com.discountlogic.data.UserData;
import com.discountlogic.model.UserDetails;
import com.discountlogic.service.DiscountServiceImpl;
import com.discountlogic.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@Api(value="UserDetails")
public class UserController {

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/user")
    @ApiOperation(value="Add User", response=UserDetails.class)
    public UserDetails saveUserDetails(@Valid @RequestBody UserData userData) throws ParseException {
        logger.info("UserController :: Request received to save the user details  :: Started");
        UserDetails userDetails = userService.saveUserDetails(userData);
        logger.info("UserController :: Request received to save the user details :: Ended");
        return userDetails;
    }


    @GetMapping("/getUser")
    public List<UserDetails> getAllUsers() {
        return userService.getAllUsers();
    }


}
