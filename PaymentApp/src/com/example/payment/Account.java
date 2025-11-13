package com.example.payment;

import java.util.Objects;

public class Account implements Comparable<Account> {
    private String accountholdername;
    private long accountno;
    private String transcode;
    private String country;
    private String ifsccode;
    private double balance;

    public Account(String accountholdername, long accountno, String transcode,
                   String country, String ifsccode, double balance) {
        this.accountholdername = accountholdername;
        this.accountno = accountno;
        this.transcode = transcode;
        this.country = country;
        this.ifsccode = ifsccode;
        this.balance = balance;
    }

    // Getters and setters
    public String getAccountholdername() { return accountholdername; }
    public long getAccountno() { return accountno; }
    public String getTranscode() { return transcode; }
    public String getCountry() { return country; }
    public String getIfsccode() { return ifsccode; }
    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return String.format("Account[name=%s, accNo=%d, balance=%.2f, ifsc=%s, country=%s, trans=%s]",
                accountholdername, accountno, balance, ifsccode, country, transcode);
    }

    // Comparable by accountholdername (lexicographic)
    @Override
    public int compareTo(Account o) {
        return this.accountholdername.compareToIgnoreCase(o.accountholdername);
    }

    // equals & hashCode based on accountholdername and accountno
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Account)) return false;
        Account other = (Account) obj;
        return this.accountno == other.accountno &&
               Objects.equals(this.accountholdername, other.accountholdername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountholdername, accountno);
    }
}
