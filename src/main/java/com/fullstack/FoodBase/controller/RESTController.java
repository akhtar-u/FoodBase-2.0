package com.fullstack.FoodBase.controller;


import com.fullstack.FoodBase.exceptions.NotFoundException;
import com.fullstack.FoodBase.exceptions.UserAlreadyExistsException;
import com.fullstack.FoodBase.exceptions.UserNotFoundException;
import com.fullstack.FoodBase.exceptions.WrongPasswordException;
import com.fullstack.FoodBase.model.IdLessRecipe;
import com.fullstack.FoodBase.model.Login;
import com.fullstack.FoodBase.model.Recipe;
import com.fullstack.FoodBase.model.Register;
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

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody @Valid Register register) throws UserAlreadyExistsException {
        log.info("Registered user with username: " + register.getUsername());
        return ResponseEntity.ok(userService.registerNewUser(register));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid Login login) throws UserNotFoundException, WrongPasswordException {
        log.info("Logged in user with email: " + login.getEmail());
        return ResponseEntity.ok(userService.loginUser(login));
    }

}
