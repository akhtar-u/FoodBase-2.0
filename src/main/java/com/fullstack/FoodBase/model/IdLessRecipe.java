package com.fullstack.FoodBase.model;

import java.util.List;

public interface IdLessRecipe {
    String getRecipeName();
    String getImageData();
    String getUsername();
    List<String> getRecipeIngredients();
    List<String> getRecipeInstructions();
}
