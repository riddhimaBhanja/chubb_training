package com.company.salary;

import java.io.*;
import java.util.*;

public class SalaryProcessor {

    private static final String FILE_PATH = "C:\\projects\\SalaryProcessingApp\\transactions.txt";

    public static void main(String[] args) {
        Account company = new Account("Company", "HDFC000001", 10000000); // company account
        Map<String, Account> employeeAccounts = new HashMap<>();

        List<Transaction> transactions = loadTransactions(FILE_PATH);

        for (Transaction t : transactions) {
            // get or create employee account
            Account empAcc = employeeAccounts.getOrDefault(
                    t.getToAcc(),
                    new Account(t.getToAcc(), t.getToAcc(), 0)
            );

            // Process
            if (company.getBalance() >= t.getAmount()) {
                company.debit(t.getAmount());
                empAcc.credit(t.getAmount());
                employeeAccounts.put(empAcc.getAccNumber(), empAcc);
                System.out.println("✅ Salary transferred: " + t);
            } else {
                System.out.println("❌ Not enough balance for: " + t);
            }
        }

        System.out.println("\n--- Final Balances ---");
        System.out.println(company);
        for (Account acc : employeeAccounts.values()) {
            System.out.println(acc);
        }
    }

    private static List<Transaction> loadTransactions(String filePath) {
        List<Transaction> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String fromAcc = parts[1];
                    String toAcc = parts[3];
                    double amt = Double.parseDouble(parts[4]);
                    list.add(new Transaction(fromAcc, toAcc, amt));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
