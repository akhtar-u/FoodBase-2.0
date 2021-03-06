package com.fullstack.FoodBase.exceptions;

public class WrongPasswordException extends Exception {
    private final String message;

    public WrongPasswordException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
