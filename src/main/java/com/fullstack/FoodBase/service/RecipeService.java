package com.fullstack.FoodBase.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fullstack.FoodBase.exceptions.JWTUsernameException;
import com.fullstack.FoodBase.exceptions.NotFoundException;
import com.fullstack.FoodBase.jwt.SecurityConstants;
import com.fullstack.FoodBase.model.IdLessRecipe;
import com.fullstack.FoodBase.model.Recipe;
import com.fullstack.FoodBase.model.SuccessResponse;
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

    public List<IdLessRecipe> getPublicRecipes() {
        return recipeRepository.findAllByRecipePublicTrue();
    }

    public SuccessResponse deleteRecipe(String id) throws NotFoundException {
        if (recipeRepository.findByRecipeID(id).get(0) == null) {
            throw new NotFoundException("No recipes found");
        }
        recipeRepository.deleteById(id);

        SuccessResponse response = new SuccessResponse();
        response.setMessage("Deleted recipe!");

        return response;
    }

    public SuccessResponse addRecipe(Recipe recipe) {
        Recipe newRecipe = new Recipe();
        newRecipe.setRecipeID(UUID.randomUUID().toString());
        newRecipe.setRecipeName(recipe.getRecipeName());
        newRecipe.setImageData(uploadImage(recipe.getImageData()));
        newRecipe.setUsername(recipe.getUsername());
        newRecipe.setRecipeIngredients(recipe.getRecipeIngredients());
        newRecipe.setRecipeInstructions(recipe.getRecipeInstructions());
        newRecipe.setRecipePublic(recipe.isRecipePublic());

        recipeRepository.save(newRecipe);

        SuccessResponse response = new SuccessResponse();
        response.setMessage("Added recipe!");

        return response;
    }

    public SuccessResponse updateRecipe(Recipe recipe) throws NotFoundException {
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

        SuccessResponse response = new SuccessResponse();
        response.setMessage("Updated recipe!");

        return response;
    }

    public List<Recipe> getRecipeByUser(String username, String token) throws JWTUsernameException {
        String user = JWT.require(Algorithm.HMAC512("${jwt.secret}".getBytes()))
                .build()
                .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                .getSubject();

        if (!user.equals(username)) {
            throw new JWTUsernameException("JWT token does not match given user!");
        }

        return recipeRepository.findByUsername(username);
    }

    private String uploadImage(String imageData) {
        if (imageData.startsWith("data:image")) {
            return s3Service.uploadFile(imageData);
        }

        return imageData;
    }
}
