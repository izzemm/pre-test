package com.priceminister.account.exceptions;

public class IllegalDrawnAmountException extends Exception {

    private String message;

    public IllegalDrawnAmountException(Double withDrawnAmount) {
        message = "The with drawn amount (" + withDrawnAmount + ") should be upper than zero";
    }

    public String getMessage() {
        return message;
    }
}
