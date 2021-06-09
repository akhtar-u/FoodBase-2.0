package com.fullstack.FoodBase.controller;


import com.fullstack.FoodBase.exceptions.*;
import com.fullstack.FoodBase.model.*;
import com.fullstack.FoodBase.service.RecipeService;
import com.fullstack.FoodBase.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/database")
public class RESTController {

    private final RecipeService recipeService;
    private final UserService userService;

    @GetMapping("/public")
    public ResponseEntity<List<IdLessRecipe>> getPublicRecipes() {
        log.info("Browse page request");
        return ResponseEntity.ok(recipeService.getPublicRecipes());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessResponse> deleteRecipe(@PathVariable String id) throws NotFoundException {
        log.info("Delete recipe request with id: " + id);
        return ResponseEntity.ok(recipeService.deleteRecipe(id));
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse> addRecipe(@RequestBody Recipe recipe) {
        log.info("Added recipe request with name: " + recipe.getRecipeName());
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @PutMapping("/update")
    public ResponseEntity<SuccessResponse> updateRecipe(@RequestBody Recipe recipe) throws NotFoundException {
        log.info("Updated recipe request with id: " + recipe.getRecipeID());
        return ResponseEntity.ok(recipeService.updateRecipe(recipe));
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<List<Recipe>> getRecipesByUser(@PathVariable String username,
                                                         @RequestHeader(name = "Authorization") String token) throws JWTUsernameException {
        log.info("Get recipes for user request with username: " + username);
        return ResponseEntity.ok(recipeService.getRecipeByUser(username, token));
    }

    @PostMapping("/registration")
    public ResponseEntity<SuccessResponse> registerUser(@RequestBody @Valid Register register) throws UserAlreadyExistsException {
        log.info("Registered user with username: " + register.getUsername());
        return ResponseEntity.ok(userService.registerNewUser(register));
    }
}
