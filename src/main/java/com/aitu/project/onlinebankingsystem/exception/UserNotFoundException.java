package com.aitu.project.onlinebankingsystem.exception;

public class UserNotFoundException extends Exception {
    private long id;

    public UserNotFoundException(long id) {
        super(String.format("User is not found with id : '%s'", id));
    }
}
