package io.github.centercode.sample.csdn;

public class CustomerException extends RuntimeException {
    public CustomerException(String kuduErrorCode, String agentErrorSys, String message) {
        super(message);
    }
}
