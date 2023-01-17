package io.jotech.banksystem.it;


import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import classicmodels.util.ArquillianWarUtils;
import io.jotech.banksystem.BankSystem;
import io.jotech.banksystem.subsystems.CreditRatingService;
import io.jotech.banksystem.subsystems.InterBankPolicyService;
import io.jotech.banksystem.subsystems.RepaymentPayabilityService;
import io.jotech.banksystem.subsystems.RepaymentService;
import io.jotech.banksystem.subsystems.TransferService;
import io.jotech.classicmodels.entity.Customer;
import io.jotech.classicmodels.entity.Employee;
import io.jotech.classicmodels.entity.Office;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(ArquillianExtension.class)
public class BankSystemItTest {

    @Deployment
    public  static Archive<?> createDeployment(){
        WebArchive webArchive= ArquillianWarUtils
                .getBasicWebArchive("BankSystemItTest.war")
                .addClasses(
                        CreditRatingService.class,
                        RepaymentPayabilityService.class,
                        RepaymentService.class,
                        TransferService.class,
                        InterBankPolicyService.class,
                        BankSystem.class
                ).addPackages(true,"io.jotech.classicmodels.entity");
        log.info(webArchive.toString(true));
        return  webArchive;
    }

    @BeforeEach
    public  void setup(){

    }

    @Inject
    private  BankSystem bankSystem;

    @Test
    @DisplayName("testCorrectInjectionOfBankSystem")
    void testCorrectInjectionOfBankSystem(){
        Assertions.assertNotNull(bankSystem);
    }
}
