package com.banking.exceptions;

public class InsufficientFundsException extends Exception {

    /**
     * Constructs a new InsufficientFundsException with the specified detail message.
     *
     * @param message The detail message, saved for later retrieval by the getMessage() method.
     */
    @SuppressWarnings("unused")
    public InsufficientFundsException(String message) {
        super(message);
    }

    /**
     * Constructs a new InsufficientFundsException with the specified detail message and cause.
     *
     * @param message The detail message, saved for later retrieval by the getMessage() method.
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     *                A null value is permitted, and indicates that the cause is nonexistent or unknown.
     */
    @SuppressWarnings("unused")
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}









