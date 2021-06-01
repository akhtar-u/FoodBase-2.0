package com.fullstack.FoodBase.controller;


import com.fullstack.FoodBase.exceptions.NotFoundException;
import com.fullstack.FoodBase.model.Recipe;
import com.fullstack.FoodBase.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://foodbase.ca"}, maxAge = 3600)
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/database")
public class RESTController {

    private final RecipeService recipeService;

    @GetMapping("/public")
    public ResponseEntity<List<Recipe>> getPublicRecipes() {
        log.info("Browse page request");
        return ResponseEntity.ok(recipeService.getPublicRecipes());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable String id) throws NotFoundException {
        log.info("Delete recipe request with id: " + id);
        return ResponseEntity.ok(recipeService.deleteRecipe(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRecipe(@RequestBody Recipe recipe) {
        log.info("Added recipe request with name: " + recipe.getRecipeName());
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateRecipe(@RequestBody Recipe recipe) throws NotFoundException {
        log.info("Updated recipe request with id: " + recipe.getRecipeID());
        return ResponseEntity.ok(recipeService.updateRecipe(recipe));
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<List<Recipe>> getRecipesByUser(@PathVariable String username) {
        log.info("Get recipes for user request with username: " + username);
        return ResponseEntity.ok(recipeService.getRecipeByUser(username));
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<Recipe> getRecipeByID(@PathVariable String id) throws NotFoundException {
        log.info("Get recipe by id: " + id);
        return ResponseEntity.ok(recipeService.getRecipeByID(id));
    }
}
