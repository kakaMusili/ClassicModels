package io.jotech.banksystem.subsystems;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import io.jotech.classicmodels.entity.Customer;

class CreditRatingServiceTest {

    CreditRatingService creditRatingService;

    @BeforeEach
    public void setup(){
        creditRatingService= new CreditRatingService();
    }
    @Test
    void checkCustomerRating() {

        var customer = Customer.builder()
                .customerNumber(1)
                .customerName("Musili")
                .city("Nairobi")
                .creditLimit(BigDecimal.valueOf(400_000))
                .build();

        var customer2 = Customer.builder()
                .customerNumber(1)
                .customerName("Musili")
                .city("Nairobi")
                .creditLimit(BigDecimal.valueOf(600_000))
                .build();

        var customer3 = Customer.builder()
                .customerNumber(1)
                .customerName("Musili")
                .city("Nairobi")
                .creditLimit(BigDecimal.valueOf(399_000))
                .build();

        //when
        boolean passes= creditRatingService.checkCustomerRating(customer);

        boolean expected= true;

        Assertions.assertAll(
                ()->Assertions.assertEquals(expected,creditRatingService.checkCustomerRating(customer)),
                ()->Assertions.assertEquals(expected,creditRatingService.checkCustomerRating(customer2)),
                ()->Assertions.assertFalse(creditRatingService.checkCustomerRating(customer3))
        );
    }
}