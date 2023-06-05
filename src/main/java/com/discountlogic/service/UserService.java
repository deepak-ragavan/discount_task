package com.discountlogic.service;

import com.discountlogic.data.UserData;
import com.discountlogic.model.UserDetails;

import java.text.ParseException;
import java.util.List;

public interface UserService {

     UserDetails saveUserDetails(UserData userData) throws ParseException;

     List<UserDetails> getAllUsers();

}
