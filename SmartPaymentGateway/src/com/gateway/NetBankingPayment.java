package com.gateway;

public class NetBankingPayment extends Payment implements Retryable {

    public NetBankingPayment(double amount) {
        super(amount);
    }

    @Override
    public void processPayment() throws InvalidAmountException, InvalidCredentialsException, TransactionFailedException {
        try {
            if (amount <= 0) throw new InvalidAmountException("Amount must be positive!");
            System.out.println("Processing NetBanking payment of ₹" + amount);

            // Simulate wrong credentials
            if (Math.random() < 0.3) throw new InvalidCredentialsException("Bank login or password incorrect!");

            // Simulate gateway timeout
            if (Math.random() < 0.2) throw new TransactionFailedException("NetBanking gateway timeout", null);

            System.out.println("NetBanking Payment successful!");
        } catch (InvalidAmountException | InvalidCredentialsException | TransactionFailedException e) {
            throw e; // propagate to main
        }
    }

    @Override
    public void retry() throws TransactionFailedException {
        System.out.println("Retrying NetBanking payment...");
        try {
            processPayment();
        } catch (Exception e) {
            throw new TransactionFailedException("Retry failed", e);
        }
    }
}
