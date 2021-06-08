package com.fullstack.FoodBase.jwt;

public class SecurityConstants {

    public static final long EXPIRATION_TIME = 300_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/database/registration";
}
