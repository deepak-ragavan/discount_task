package com.discountlogic.controller;

import com.discountlogic.data.PurchaseDetails;
import com.discountlogic.exception.UserNotFoundException;
import com.discountlogic.service.DiscountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@Api(value="Discount")
public class DiscountController {

    @Autowired
    DiscountService discountService;

    Logger logger = LoggerFactory.getLogger(DiscountController.class);

    @GetMapping("/discountedBillAmount")
    @ApiOperation(value="Apply Discount to the bill amount", response= BigDecimal.class)
    public BigDecimal calculateFinalBillAmount(@Valid @RequestBody PurchaseDetails purchaseDetails) throws UserNotFoundException {
        logger.info("DiscountController :: Request received to calculate final bill amount :: Started");
        BigDecimal finalAmount = discountService.calculateFinalAmount(purchaseDetails);
        logger.info("DiscountController :: Request received to calculate final bill amount :: Ended");
        return finalAmount;
    }
}
