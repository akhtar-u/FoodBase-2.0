package com.fullstack.FoodBase.service;

import com.fullstack.FoodBase.model.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class RecipeService {

    @Autowired
    private final RecipeRepository recipeRepository;


}
