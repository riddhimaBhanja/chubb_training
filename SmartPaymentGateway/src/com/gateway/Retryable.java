package com.gateway;

public interface Retryable {
    void retry() throws TransactionFailedException;

	void retry();
}
