package com.fullstack.FoodBase.model;

import lombok.Data;

@Data
public class Register {

    private String username;
    private String email;
    private String emailConfirm;
    private String password;
    private String passwordConfirm;
}
