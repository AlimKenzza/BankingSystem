package com.aitu.project.onlinebankingsystem.exception;

public class TransactionNotFoundException extends Exception{
    private long id;

    public TransactionNotFoundException(long id) {
        super(String.format("Transaction is not found with id : '%s'", id));
    }
}
