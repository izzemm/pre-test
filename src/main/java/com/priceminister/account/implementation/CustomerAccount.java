package com.priceminister.account.implementation;

import com.priceminister.account.*;
import com.priceminister.account.IllegalBalanceException;
import com.priceminister.account.IllegalDrawnAmountException;
import com.priceminister.account.RuleNotInitializedException;


public class CustomerAccount implements Account {

    private Double balance = 0.0;

    public void add(Double addedAmount) {
        balance = addedAmount;
    }

    public Double getBalance() {
        return balance;
    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule)
            throws IllegalBalanceException, RuleNotInitializedException, IllegalDrawnAmountException {
        if (withdrawnAmount <= 0) {
            throw new IllegalDrawnAmountException(withdrawnAmount);
        }
        if (rule != null) {
            Double resultingAccountBalance = balance - withdrawnAmount;
            if (rule.withdrawPermitted(resultingAccountBalance)) {
                balance = resultingAccountBalance;
                return withdrawnAmount;
            } else {
                throw new IllegalBalanceException(withdrawnAmount);
            }
        } else {
            throw new RuleNotInitializedException("Rule not initialized");
        }
    }
}
