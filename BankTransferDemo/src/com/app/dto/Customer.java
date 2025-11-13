package com.app.dto;

public class Customer {

    private String name;
    private String email;
    private String accountNo;
    private double amountBalance;

    public Customer(String string, String string2, String string3, int i) {
        System.out.println("inside default constructor");
    }

    public Customer(String name, String email, String accountNo, double balance) {
        this.name = name;
        this.email = email;
        this.accountNo = accountNo;
        this.amountBalance = balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public double getAmountBalance() {
        return amountBalance;
    }

    public void setAmount(double amount) {
        this.amountBalance = amount;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', email='" + email + "', accountNo='" + accountNo +
               "', balance=" + amountBalance + "}";
    }
}