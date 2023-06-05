package com.discountlogic.service;

import com.discountlogic.controller.DiscountController;
import com.discountlogic.data.PurchaseDetails;
import com.discountlogic.exception.UserNotFoundException;
import com.discountlogic.enumclasses.ProductsType;
import com.discountlogic.enumclasses.UserType;
import com.discountlogic.repository.UserRepository;
import com.discountlogic.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(DiscountServiceImpl.class);

    @Override
    public BigDecimal calculateFinalAmount(PurchaseDetails purchaseDetails) throws UserNotFoundException {
        logger.info("DiscountServiceImpl :: Request received to calculate final bill amount :: Started");
        BigDecimal billAmount = purchaseDetails.getBillAmount();
        UserDetails userDetails = userRepository.findByEmail(purchaseDetails.getEmail());
        if (userDetails == null) {
            throw new UserNotFoundException("User Not Found");
        }
        BigDecimal discountAmountPerHundredDollar = new BigDecimal("5");

        if (!purchaseDetails.getProductsType().equalsIgnoreCase(String.valueOf(ProductsType.GROCERY))) {
            if (userDetails.getUserType().equalsIgnoreCase(String.valueOf(UserType.EMPLOYEE))) {
                billAmount = nonGroceryProductDiscount(billAmount, BigDecimal.valueOf(30l));
            } else if (userDetails.getUserType().equalsIgnoreCase(String.valueOf(UserType.AFFILIATE))) {
                billAmount = nonGroceryProductDiscount(billAmount, BigDecimal.valueOf(10l));
            } else if (userDetails.getUserType().equalsIgnoreCase(String.valueOf(UserType.CUSTOMER))) {
                long noOfYears = userDetails.getPurchaseStartDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate().until(LocalDate.now(), ChronoUnit.YEARS);
                if (noOfYears >= 2) {
                    billAmount = nonGroceryProductDiscount(billAmount, BigDecimal.valueOf(5l));
                }
            }
        }
        BigDecimal commonDiscountAmount = billAmount.divide(BigDecimal.valueOf(100l), RoundingMode.FLOOR)
                .multiply(discountAmountPerHundredDollar);

        logger.info("DiscountServiceImpl :: Request received to calculate final bill amount :: Ended");
        return billAmount.subtract(commonDiscountAmount);
    }

    private BigDecimal nonGroceryProductDiscount(BigDecimal billAmount,BigDecimal percentage) {
        logger.info("DiscountServiceImpl :: Request received to calculate non-Grocery product discount amount :: Started");
        BigDecimal discountAmount = billAmount.multiply(percentage).divide(BigDecimal.valueOf(100l));
        logger.info("DiscountServiceImpl :: Request received to calculate non-Grocery product discount amount :: Ended");
        return billAmount.subtract(discountAmount);
    }

}
