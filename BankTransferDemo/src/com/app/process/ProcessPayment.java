package com.app.process;

import com.app.dto.Customer;

public abstract class ProcessPayment implements EmailProcessing {

    protected int processCount;

    /** Subclasses implement the actual fund logic and may throw exceptions. */
    public abstract void processFund(Customer initiator, Customer beneficiary, double amount) throws Exception;

    /** Subclasses decide customer validity. */
    public abstract boolean validateCustomer(Customer c1);

    /** Simple email validation utility fulfilling EmailProcessing. */
    @Override
    public boolean validateEmail(Customer c1) {
        return c1 != null && c1.getEmail() != null && c1.getEmail().contains("@");
    }

    /** Override default to show different server provider. */
    @Override
    public void initializeEmailServer() {
        System.out.println("initialize server with azure email service");
    }

    /** Helper to actually move money once checks pass. */
    protected void transfer(Customer from, Customer to, double amount) {
        double balanceAmount = from.getAmountBalance() - amount;
        from.setAmount(balanceAmount);
        to.setAmount(to.getAmountBalance() + amount);
    }
}