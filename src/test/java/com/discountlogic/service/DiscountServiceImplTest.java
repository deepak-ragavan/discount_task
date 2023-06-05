package com.discountlogic.service;

import com.discountlogic.data.PurchaseDetails;
import com.discountlogic.enumclasses.ProductsType;
import com.discountlogic.enumclasses.UserType;
import com.discountlogic.exception.UserNotFoundException;
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@ExtendWith(MockitoExtension.class)
public class DiscountServiceImplTest {

    @InjectMocks
    private DiscountServiceImpl discountServiceImpl;

    @Mock
    UserRepository userRepository;

    private UserDetails userDetails;

    private PurchaseDetails purchaseDetails;

    @BeforeEach
    public void setup() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        userDetails = new UserDetails("testing","tesing@gmail.com","employee",formatter.parse("15-04-2020"));
        purchaseDetails = new PurchaseDetails("tesing@gmail.com",BigDecimal.valueOf(950l),String.valueOf(ProductsType.NONGROCERY));
    }

    @Test
    public void calculateFinalAmountGroceryEmployeeTest() throws UserNotFoundException {
        Mockito.when(userRepository.findByEmail("tesing@gmail.com")).thenReturn(userDetails);
        BigDecimal finalAmount = discountServiceImpl.calculateFinalAmount(purchaseDetails);
        Assertions.assertEquals(BigDecimal.valueOf(635),finalAmount);
    }

    @Test
    public void calculateFinalAmountGroceryAffiliateTest() throws UserNotFoundException {
        userDetails.setUserType(String.valueOf(UserType.AFFILIATE));
        Mockito.when(userRepository.findByEmail("tesing@gmail.com")).thenReturn(userDetails);
        BigDecimal finalAmount = discountServiceImpl.calculateFinalAmount(purchaseDetails);
        Assertions.assertEquals(BigDecimal.valueOf(815),finalAmount);
    }

    @Test
    public void calculateFinalAmountGroceryCustomerTest() throws UserNotFoundException {
        userDetails.setUserType(String.valueOf(UserType.CUSTOMER));
        Mockito.when(userRepository.findByEmail("tesing@gmail.com")).thenReturn(userDetails);
        BigDecimal finalAmount = discountServiceImpl.calculateFinalAmount(purchaseDetails);
        Assertions.assertEquals(BigDecimal.valueOf(857.5),finalAmount);
    }



}
