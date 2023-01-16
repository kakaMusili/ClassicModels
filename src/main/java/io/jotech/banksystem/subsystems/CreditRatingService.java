package io.jotech.banksystem.subsystems;

import java.math.BigDecimal;

import io.jotech.classicmodels.entity.Customer;

public class CreditRatingService {
    public boolean checkCustomerRating(Customer customer){

        return customer.getCreditLimit().compareTo( BigDecimal.valueOf(400_000)) >= 0;
    }
}
