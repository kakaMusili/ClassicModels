package io.jotech.banksystem.subsystems;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepaymentPayabilityServiceTest {

    RepaymentPayabilityService repaymentPayabilityService;

    @BeforeEach
    public void setup(){
        repaymentPayabilityService= new RepaymentPayabilityService();
    }
    @Test
    void calculatePayability() {

        var principal= BigDecimal.valueOf(450_000);
        var income= BigDecimal.valueOf(50_000);
        var income2= BigDecimal.valueOf(300_000);
        var months = 6;

        Assertions.assertAll(
                ()-> Assertions.assertFalse(repaymentPayabilityService.calculatePayability(principal, income, months))
                ,()->  Assertions.assertTrue(repaymentPayabilityService.calculatePayability(principal, income2, months))
        );

    }
}