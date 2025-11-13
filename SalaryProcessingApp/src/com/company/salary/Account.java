package com.company.salary;

public class Account {
    private String accHolderName;
    private String accNumber;
    private double balance;

    public Account(String accHolderName, String accNumber, double balance) {
        this.accHolderName = accHolderName;
        this.accNumber = accNumber;
        this.balance = balance;
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void credit(double amount) {
        balance += amount;
    }

    public void debit(double amount) {
        balance -= amount;
    }

    @Override
    public String toString() {
        return accHolderName + " (" + accNumber + ") - Balance: " + balance;
    }
}
