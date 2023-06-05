package com.discountlogic.service;

import com.discountlogic.data.UserData;
import com.discountlogic.model.UserDetails;
import com.discountlogic.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepository;
    private UserData userData;
    private UserDetails userDetails;

    @BeforeEach
    public void setup() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        userData = new UserData("testing","tesing@gmail.com","employee","15-04-2020");
        userDetails = new UserDetails("testing","tesing@gmail.com","employee",formatter.parse("15-04-2020"));
    }

    @Test
    public void saveUserDetails() throws ParseException {
        Mockito.when(userRepository.save(any(UserDetails.class))).thenReturn(userDetails);
        UserDetails userDetailsTest = userServiceImpl.saveUserDetails(userData);
        Assertions.assertNotNull(userDetailsTest);
    }

}
