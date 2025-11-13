package com.gateway;

public class CreditCardPayment extends Payment implements Retryable {

    public CreditCardPayment(double amount) {
        super(amount);
    }

    @Override
    public void processPayment() throws InvalidAmountException, InvalidCredentialsException, TransactionFailedException {
        try {
            if (amount <= 0) throw new InvalidAmountException("Amount must be positive!");
            System.out.println("Processing Credit Card payment of ₹" + amount);

            // Simulate invalid credentials randomly
            if (Math.random() < 0.3) throw new InvalidCredentialsException("Credit card number or CVV invalid!");

            // Simulate gateway timeout randomly
            if (Math.random() < 0.2) throw new TransactionFailedException("Credit card gateway timeout", null);

            System.out.println("Credit Card Payment successful!");
        } catch (InvalidAmountException | InvalidCredentialsException | TransactionFailedException e) {
            throw e; // rethrow to main
        }
    }

    @Override
    public void retry() throws TransactionFailedException {
        System.out.println("Retrying Credit Card payment...");
        try {
            processPayment();
        } catch (Exception e) {
            throw new TransactionFailedException("Retry failed", e);
        }
    }
}
