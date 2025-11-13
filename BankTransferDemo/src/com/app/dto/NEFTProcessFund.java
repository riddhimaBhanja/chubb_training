package com.app.dto;

import com.app.process.ProcessPayment;
import com.app.process.SMSProcessing;

public class NEFTProcessFund extends ProcessPayment implements SMSProcessing {

    // Example limit for demo purposes; adjust/remove as needed.
    private static final double NEFT_LIMIT = 2_000_000.00;

    @Override
    public void processFund(Customer initiator, Customer beneficiary, double amount) throws AccountBalanceException {
        System.out.println("Hi this is first program in NEFTProcessFund");

        if (initiator == null || beneficiary == null) {
            throw new AccountBalanceException("Initiator or beneficiary is null");
        }
        if (amount <= 0) {
            throw new AccountBalanceException("Amount must be positive");
        }
        // Demo rule: amount should not exceed NEFT_LIMIT and initiator must have funds
        if (initiator.getAmountBalance() >= amount && amount <= NEFT_LIMIT) {
            transfer(initiator, beneficiary, amount);
            System.out.println("Process fund immediately via NEFT");
            sendSMS(initiator);
        } else {
            throw new AccountBalanceException("Insufficient balance or amount exceeds NEFT limit");
        }
    }

    @Override
    public boolean validateCustomer(Customer c1) {
        return c1 != null && c1.getName() != null && !c1.getName().equalsIgnoreCase("Bin Laden");
    }

    @Override
    public boolean sendSMS(Customer c1) {
        System.out.println("sent sms to customer " + (c1 != null ? c1.getName() : "UNKNOWN"));
        return true;
    }
}