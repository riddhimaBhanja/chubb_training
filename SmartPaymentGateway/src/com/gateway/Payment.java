package com.gateway;

public abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract void processPayment() throws InvalidAmountException,
            InsufficientBalanceException,
            InvalidCredentialsException,
            PaymentGatewayTimeoutException,
            TransactionFailedException;
}
