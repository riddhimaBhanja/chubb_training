package com.gateway;

import java.util.Scanner;

public class PaymentApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       
        System.out.print("Enter beneficiary name: ");
        String beneficiary = sc.nextLine().trim();

       
        if (beneficiary.isEmpty()) {
            System.out.println("Error: Beneficiary not found!");
            sc.close();
            return;
        }

       
        System.out.print("Choose payment method (UPI / Wallet / CreditCard / NetBanking): ");
        String method = sc.nextLine().trim();

      
        System.out.print("Enter amount to pay: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        
        String credential = "";
        String password = "";
        if (method.equalsIgnoreCase("UPI")) {
            System.out.print("Enter UPI ID: ");
            credential = sc.nextLine();
            System.out.print("Enter UPI PIN: ");
            password = sc.nextLine();
        } else if (method.equalsIgnoreCase("CreditCard")) {
            System.out.print("Enter Card Number: ");
            credential = sc.nextLine();
            System.out.print("Enter CVV / Password: ");
            password = sc.nextLine();
        } else if (method.equalsIgnoreCase("NetBanking")) {
            System.out.print("Enter Bank ID: ");
            credential = sc.nextLine();
            System.out.print("Enter Bank Password: ");
            password = sc.nextLine();
        } else if (method.equalsIgnoreCase("Wallet")) {
            System.out.println("Using wallet balance.");
        } else {
            System.out.println("Invalid payment method selected.");
            sc.close();
            return;
        }

        // 5️⃣ Create Payment object
        Payment payment = null;
        switch (method.toLowerCase()) {
            case "upi":
                payment = new UPIPayment(amount);
                break;
            case "wallet":
                payment = new WalletPayment(amount);
                break;
            case "creditcard":
                payment = new CreditCardPayment(amount);
                break;
            case "netbanking":
                payment = new NetBankingPayment(amount);
                break;
        }

        System.out.println("\nProcessing payment for beneficiary: " + beneficiary);

     
        try {
            payment.processPayment();
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
            // Refund if supported
            if (payment instanceof Refundable) {
                try {
                    ((Refundable) payment).refund();
                } catch (TransactionFailedException ex) {
                    System.out.println("Refund failed: " + ex.getMessage());
                }
            }
        } catch (InvalidCredentialsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Sensitive info masked in logs.");
        } catch (TransactionFailedException e) {
            System.out.println("Error: Transaction failed -> " + e.getMessage());
            // Retry if supported
            if (payment instanceof Retryable) {
                try {
                    ((Retryable) payment).retry();
                } catch (TransactionFailedException ex) {
                    System.out.println("Retry also failed: " + ex.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        System.out.println("\nPayment processing finished.");
        sc.close();
    }
}
