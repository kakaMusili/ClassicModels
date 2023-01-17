package io.jotech.banksystem;

import java.math.BigDecimal;

import io.jotech.banksystem.subsystems.CreditRatingService;
import io.jotech.banksystem.subsystems.InterBankPolicyService;
import io.jotech.banksystem.subsystems.RepaymentPayabilityService;
import io.jotech.banksystem.subsystems.RepaymentService;
import io.jotech.banksystem.subsystems.TransferService;
import io.jotech.classicmodels.entity.Customer;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor

public class BankSystem {
    @Inject
    private   CreditRatingService creditRatingService;

    @Inject
    private   InterBankPolicyService interBankPolicyService;

    @Inject
    private   RepaymentService repaymentService;

    @Inject
    private   RepaymentPayabilityService repaymentPayabilityService;

    @Inject
    private    TransferService transferService;

   /*  public BankSystem(CreditRatingService creditRatingService, InterBankPolicyService interBankPolicyService,
                      RepaymentService repaymentService, RepaymentPayabilityService repaymentPayabilityService, TransferService transferService) {

        this.creditRatingService=creditRatingService;
        this.interBankPolicyService=interBankPolicyService;
        this.repaymentService=repaymentService;
        this.repaymentPayabilityService=repaymentPayabilityService;
        this.

    } */


    public boolean processLoanApplication(Customer customer,
                                          BigDecimal principal,
                                          BigDecimal income,
                                          Integer months) {
        boolean passes = creditRatingService.checkCustomerRating(customer);
        if (!passes) {
            return false;
        }

        boolean affordable = repaymentPayabilityService.calculatePayability(principal, income, months);

        if (!affordable) {
            return false;
        }

        boolean compliant = interBankPolicyService.checkCompliance(customer);

        if (!compliant) {
            return false;
        }

        //application successfull, transer the money to customer account
        transferService.makeTransfer(principal, customer);
        repaymentService.setupPaymentSchedule(customer, income, months);

        return true;
    }
}
