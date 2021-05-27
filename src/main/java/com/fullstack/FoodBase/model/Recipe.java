package com.fullstack.FoodBase.model;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class Recipe {

    @Id
    private String recipeID;
    private String recipeName;
    private String imageURL;
    private boolean recipePublic;
    @ElementCollection
    private List<String> recipeIngredients;
    @ElementCollection
    private List<String> recipeInstructions;
}
