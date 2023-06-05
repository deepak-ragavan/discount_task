package com.discountlogic.data;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseDetails {

    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotNull
    private BigDecimal billAmount;

    @NotNull
    @NotEmpty
    @NotBlank
    private String productsType;
}
