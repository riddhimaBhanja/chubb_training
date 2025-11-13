package com.gateway;

public class UPIPayment extends Payment implements Retryable {

    public UPIPayment(double amount) { super(amount); }

    @Override
    public void processPayment() throws InvalidAmountException, TransactionFailedException {
        try {
            if (amount <= 0) throw new InvalidAmountException("Amount must be positive!");
            System.out.println("Processing UPI payment of ₹" + amount);
            if (Math.random() < 0.3) throw new TransactionFailedException("UPI Timeout", null);
            System.out.println("UPI Payment successful!");
        } catch (InvalidAmountException | TransactionFailedException e) {
            throw e;
        }
    }

    @Override
    public void retry() throws TransactionFailedException {
        System.out.println("Retrying UPI payment...");
        try {
            processPayment();
        } catch (Exception e) {
            throw new TransactionFailedException("Retry failed", e);
        }
    }
}
