package com.fullstack.FoodBase.exceptions;

public class JWTUsernameException extends Exception {
    private final String message;

    public JWTUsernameException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
