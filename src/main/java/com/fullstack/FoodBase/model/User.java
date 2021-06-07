package com.fullstack.FoodBase.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    private String username;
    private String email;
    private String password;
}
