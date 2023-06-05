package com.discountlogic.service;

import com.discountlogic.controller.UserController;
import com.discountlogic.data.UserData;
import com.discountlogic.model.UserDetails;
import com.discountlogic.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDetails saveUserDetails(UserData userData) throws ParseException {
        logger.info("UserServiceImpl :: Request received to save the user details :: Started");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        UserDetails userDetails = new UserDetails(userData.getName(), userData.getEmail(), userData.getUserType(), formatter.parse(userData.getPurchaseStartDate()));
        UserDetails savedUserDetails= userRepository.save(userDetails);
        logger.info("UserServiceImpl :: Request received to save the user details :: Ended");
        return savedUserDetails;
    }

    @Override
    public List<UserDetails> getAllUsers() {
        return userRepository.findAll();
    }
}
