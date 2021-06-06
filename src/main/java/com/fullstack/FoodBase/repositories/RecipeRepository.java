package com.fullstack.FoodBase.repositories;

import com.fullstack.FoodBase.model.IdLessRecipe;
import com.fullstack.FoodBase.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, String> {
    List<IdLessRecipe> findAllByRecipePublicTrue();
    List<Recipe> findByRecipeID(String recipeID);
    List<Recipe> findByUsername(String username);
}
