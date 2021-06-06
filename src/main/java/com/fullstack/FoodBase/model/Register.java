package com.fullstack.FoodBase.model;

import com.fullstack.FoodBase.validation.EmailMatches;
import com.fullstack.FoodBase.validation.PasswordMatches;
import com.fullstack.FoodBase.validation.ValidEmail;
import com.fullstack.FoodBase.validation.ValidUsername;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
@EmailMatches
@Entity
public class Register {

    @ValidUsername
    @NotNull
    @NotBlank
    @Id
    private String username;

    @ValidEmail
    @NotNull
    @NotBlank
    private String email;
    @Transient
    private String emailConfirm;

    @NotNull
    @NotBlank
    @Size(min = 8)
    private String password;
    @Transient
    private String passwordConfirm;
}
