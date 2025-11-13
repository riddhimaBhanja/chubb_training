package com.example.payment;

import java.util.Comparator;

public class AccountNoComparator implements Comparator<Account> {
    @Override
    public int compare(Account a1, Account a2) {
        return Long.compare(a1.getAccountno(), a2.getAccountno());
    }
}
