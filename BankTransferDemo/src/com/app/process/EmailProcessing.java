package com.app.process;

import com.app.dto.Customer;

public interface EmailProcessing {

    boolean validateEmail(Customer c1);

    static boolean sendEmail() {
        System.out.println("sending emails");
        return true;
    }

    default void initializeEmailServer() {
        System.out.println("initialize server");
    }
}