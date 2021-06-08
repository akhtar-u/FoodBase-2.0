package com.fullstack.FoodBase.model;

import com.fullstack.FoodBase.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Login {

    @ValidEmail
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 8)
    private String password;
}
