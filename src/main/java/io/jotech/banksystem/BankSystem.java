package io.jotech.banksystem;

import java.math.BigDecimal;

import io.jotech.banksystem.subsystems.CreditRatingService;
import io.jotech.banksystem.subsystems.InterBankPolicyService;
import io.jotech.banksystem.subsystems.RepaymentPayabilityService;
import io.jotech.banksystem.subsystems.RepaymentService;
import io.jotech.banksystem.subsystems.TransferService;
import io.jotech.classicmodels.entity.Customer;

public class BankSystem {

    CreditRatingService creditRatingService = new CreditRatingService();

    InterBankPolicyService interBankPolicyService = new InterBankPolicyService();

    RepaymentService repaymentService = new RepaymentService();

    RepaymentPayabilityService repaymentPayabilityService = new RepaymentPayabilityService();

    TransferService transferService = new TransferService();

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
