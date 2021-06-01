package com.fullstack.FoodBase.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
    List<Recipe> findAllByRecipePublicTrue();
    List<Recipe> findByRecipeID(String recipeID);
    List<Recipe> findByUsername(String username);

}