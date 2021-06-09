package com.fullstack.FoodBase.exceptions;

public class NotFoundException extends Exception {
    private final String message;

    public NotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
