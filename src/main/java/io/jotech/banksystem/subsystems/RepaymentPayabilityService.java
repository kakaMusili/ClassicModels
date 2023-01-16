package io.jotech.banksystem.subsystems;

import java.math.BigDecimal;
import java.math.MathContext;

public class RepaymentPayabilityService {


    public boolean calculatePayability(BigDecimal principal , BigDecimal income,Integer months){

        BigDecimal interestRate=BigDecimal.valueOf(0.1);
        BigDecimal interest= interestRate.multiply(principal).multiply(BigDecimal.valueOf(Double.valueOf( months)/12));

        var repaymentAmount= principal.add(interest);

        var installments = repaymentAmount.divide(BigDecimal.valueOf(months), MathContext.DECIMAL32);

        var thirtyPrctIncome=BigDecimal.valueOf(0.3).multiply(income);

        return thirtyPrctIncome.compareTo(installments)>=0;

    }
}
