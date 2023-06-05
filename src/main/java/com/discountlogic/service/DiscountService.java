package com.discountlogic.service;

import com.discountlogic.data.PurchaseDetails;
import com.discountlogic.exception.UserNotFoundException;
import java.math.BigDecimal;

public interface DiscountService {

    public BigDecimal calculateFinalAmount(PurchaseDetails purchaseDetails) throws UserNotFoundException;
}
