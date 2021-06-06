package com.fullstack.FoodBase.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Register {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String email;
    private String emailConfirm;

    @NotNull
    @NotBlank
    private String password;
    private String passwordConfirm;
}
