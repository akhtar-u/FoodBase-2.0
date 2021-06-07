package com.fullstack.FoodBase.exceptions;

public class UserAlreadyExistsException extends Exception {
    private String message;

    public UserAlreadyExistsException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
