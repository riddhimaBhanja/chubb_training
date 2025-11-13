package com.example.payment;

import java.util.*;

public class PaymentApp {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Richa Sharma", 1002003001L, "T01", "India", "SBIN0001234", 15000.50));
        accounts.add(new Account("Aashish Choudhary", 1002003003L, "T02", "India", "HDFC0004321", 52000.00));
        accounts.add(new Account("Bhanu Patel", 1002003002L, "T03", "India", "ICIC0009876", 2400.75));
        accounts.add(new Account("Richa Sharma", 1002003001L, "T04", "India", "SBIN0001234", 15000.50)); // duplicate

        System.out.println("Original list:");
        accounts.forEach(System.out::println);

        // Use hash-based set to demonstrate equals+hashCode behavior
        Set<Account> set = new HashSet<>(accounts);
        System.out.println("\nUnique accounts (HashSet, duplicates removed if name+no match):");
        set.forEach(System.out::println);

        // Sort by accountholdername using Comparable (natural order)
        List<Account> byName = new ArrayList<>(set);
        Collections.sort(byName); // uses compareTo in Account
        System.out.println("\nSorted by accountholdername (Comparable):");
        byName.forEach(System.out::println);

        // Sort by account number using AccountNoComparator
        List<Account> byAccNo = new ArrayList<>(set);
        byAccNo.sort(new AccountNoComparator());
        System.out.println("\nSorted by account number (AccountNoComparator):");
        byAccNo.forEach(System.out::println);

        // Sort by balance using BalanceComparator
        List<Account> byBalance = new ArrayList<>(set);
        byBalance.sort(new BalanceComparator());
        System.out.println("\nSorted by balance (BalanceComparator):");
        byBalance.forEach(System.out::println);

        // Example of stream-based sort (descending balance)
        System.out.println("\nSorted by balance descending (stream):");
        byBalance.stream()
                 .sorted((a,b) -> Double.compare(b.getBalance(), a.getBalance()))
                 .forEach(System.out::println);
    }
}
