package com.fullstack.FoodBase.exceptions;

public class UserNotFoundException extends Exception {
    private final String message;

    public UserNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
