package com.appp;

import com.app.dto.AccountBalanceException;
import com.app.dto.Customer;
import com.app.dto.NEFTProcessFund;

public class FundTransfer {

    public int count; // heap

    public static void main(String[] args) {
        System.out.println("This is test");

        FundTransfer fdobj = new FundTransfer(); // just to show an instance variable exists
        if (fdobj != null) { /* no-op */ }

        Customer c1 = new Customer("James", "james@gmail.com", "43432432442", 4343);
        Customer c2 = new Customer("Robin", "robin@gmail.com", "43432432441", 50_000);

        NEFTProcessFund neft = new NEFTProcessFund();

        System.out.println("customer balance initiator: " + c1.getAmountBalance());
        System.out.println("customer balance beneficiary: " + c2.getAmountBalance());

        boolean isValidInitiator = neft.validateCustomer(c1);
        boolean isValidBeneficiary = neft.validateCustomer(c2);

        if (isValidInitiator && isValidBeneficiary) {
            try {
                neft.processFund(c1, c2, 30_000);
                System.out.println("Transfer successful.");
            } catch (AccountBalanceException e) {
                System.out.println("Transfer failed: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("One or both customers are not valid.");
        }

        System.out.println("customer balance initiator (after): " + c1.getAmountBalance());
        System.out.println("customer balance beneficiary (after): " + c2.getAmountBalance());
    }
}