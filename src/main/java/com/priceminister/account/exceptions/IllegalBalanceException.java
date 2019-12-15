package com.priceminister.account.exceptions;


public class IllegalBalanceException extends Exception {
    
    private static final long serialVersionUID = -9204191749972551939L;
    
	private Double balance;

    private String message;
    
    public IllegalBalanceException(Double illegalBalance) {
        balance = illegalBalance;
        message = "Illegal account balance: " + balance;
    }
    public String getMessage() {
        return message;
    }
    public String toString() {
        return "Illegal account balance: " + balance;
    }
}
