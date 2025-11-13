package com.gateway;

public interface Refundable {
    void refund() throws TransactionFailedException;
}
