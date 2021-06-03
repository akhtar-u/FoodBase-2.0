package com.fullstack.FoodBase.service;

import com.fullstack.FoodBase.exceptions.NotFoundException;
import com.fullstack.FoodBase.model.Recipe;
import com.fullstack.FoodBase.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class RecipeService {

    @Autowired
    private final RecipeRepository recipeRepository;
    @Autowired
    private final S3Service s3Service;

    public List<Recipe> getPublicRecipes(){
        return recipeRepository.findAllByRecipePublicTrue();
    }

    public String deleteRecipe(String id) throws NotFoundException {
        if (recipeRepository.findByRecipeID(id).get(0) == null) {
            throw new NotFoundException("No recipes found");
        }
        recipeRepository.deleteById(id);
        return "Deleted recipe with id: " + id;
    }

    public String addRecipe(Recipe recipe) {
        Recipe newRecipe = new Recipe();
        newRecipe.setRecipeID(UUID.randomUUID().toString());
        newRecipe.setRecipeName(recipe.getRecipeName());
        newRecipe.setImageData(uploadImage(recipe.getImageData()));
        newRecipe.setUsername(recipe.getUsername());
        newRecipe.setRecipeIngredients(recipe.getRecipeIngredients());
        newRecipe.setRecipeInstructions(recipe.getRecipeInstructions());
        newRecipe.setRecipePublic(recipe.isRecipePublic());

        recipeRepository.save(newRecipe);

        return "Added recipe with name: " + recipe.getRecipeName();
    }

    public String updateRecipe(Recipe recipe) throws NotFoundException {
        if (recipeRepository.findByRecipeID(recipe.getRecipeID()) == null) {
            throw new NotFoundException("Recipe does not exist!");
        }
        Recipe currentRecipe = recipeRepository.findByRecipeID(recipe.getRecipeID()).get(0);
        currentRecipe.setRecipeName(recipe.getRecipeName());
        currentRecipe.setImageData(uploadImage(recipe.getImageData()));
        currentRecipe.setRecipeIngredients(recipe.getRecipeIngredients());
        currentRecipe.setRecipeInstructions(recipe.getRecipeInstructions());
        currentRecipe.setRecipePublic(recipe.isRecipePublic());

        recipeRepository.save(currentRecipe);

        return "Updated recipe with id: " + currentRecipe.getRecipeID();
    }

    public List<Recipe> getRecipeByUser(String username) {
        return recipeRepository.findByUsername(username);
    }

    public Recipe getRecipeByID(String id) throws NotFoundException {
        if (recipeRepository.findByRecipeID(id).get(0) == null) {
            throw new NotFoundException("No recipe found");
        }

        return recipeRepository.findByRecipeID(id).get(0);
    }

    private String uploadImage(String imageData) {
        if(imageData.startsWith("data:image")){
            return s3Service.uploadFile(imageData);
        }

        return imageData;
    }
}
