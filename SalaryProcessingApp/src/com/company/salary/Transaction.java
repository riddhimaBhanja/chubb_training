package com.company.salary;

public class Transaction {
    private String fromAcc;
    private String toAcc;
    private double amount;

    public Transaction(String fromAcc, String toAcc, double amount) {
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.amount = amount;
    }

    public String getFromAcc() {
        return fromAcc;
    }

    public String getToAcc() {
        return toAcc;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return fromAcc + " -> " + toAcc + " : " + amount;
    }
}
