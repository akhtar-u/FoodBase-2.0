package com.fullstack.FoodBase.controller;


import com.fullstack.FoodBase.exceptions.NotFoundException;
import com.fullstack.FoodBase.model.Recipe;
import com.fullstack.FoodBase.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/database")
public class RESTController {

    private final RecipeService recipeService;

    @GetMapping("/allrecipes")
    public ResponseEntity<List<Recipe>> browse() throws NotFoundException {
        log.info("Browse page request");
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

}
