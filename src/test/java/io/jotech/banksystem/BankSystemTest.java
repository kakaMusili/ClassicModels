package io.jotech.banksystem;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import io.jotech.classicmodels.entity.Customer;

class BankSystemTest {

    BankSystem bankSystem;

    @BeforeEach
            public  void setup()
    {
        bankSystem=new BankSystem();
    }

    @Test
    void processLoanApplication() {

// given
        var principal= BigDecimal.valueOf(450_000);
        var income= BigDecimal.valueOf(50_000);
        var income2= BigDecimal.valueOf(300_000);
        var months = 6;
        var customer = Customer.builder()
                .customerNumber(1)
                .customerName("Musili")
                .city("Nairobi")
                .creditLimit(BigDecimal.valueOf(600_000))
                .build();

        //when
        bankSystem= new BankSystem();

        boolean isProsessed= bankSystem.processLoanApplication(customer,principal, income, months);

        //then
        boolean expected=false;

        //confirmation
        Assertions.assertEquals(expected,
                isProsessed,
                ()->"The Loan should not be processed given " +
                "the principal is + " + principal + " and income is " + income);

        Assertions.assertTrue(
                bankSystem.processLoanApplication(customer,principal, income2, months),
                ()->"The Loan should not be processed given " +
                        "the principal is + " + principal + " and income is " + income);

    }
}