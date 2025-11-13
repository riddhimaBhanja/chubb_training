package com.gateway;

public class WalletPayment extends Payment implements Refundable, Retryable {
    private double walletBalance = 100;

    public WalletPayment(double amount) { super(amount); }

    @Override
    public void processPayment() throws InsufficientBalanceException, InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be positive!");
        if (amount > walletBalance) throw new InsufficientBalanceException("Wallet balance insufficient!");
        walletBalance -= amount;
        System.out.println("Wallet Payment successful! Remaining balance: ₹" + walletBalance);
    }

    @Override
    public void refund() {
        walletBalance += amount;
        System.out.println("Refunded ₹" + amount + " to wallet. Current balance: ₹" + walletBalance);
    }

    @Override
    public void retry() {
        System.out.println("Retrying wallet payment...");
    }
}
