package com.fullstack.FoodBase.service;

import com.fullstack.FoodBase.exceptions.NotFoundException;
import com.fullstack.FoodBase.model.Recipe;
import com.fullstack.FoodBase.model.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RecipeService {

    @Autowired
    private final RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes() throws NotFoundException {
        if (recipeRepository.findAllByRecipePublicTrue() == null) {
            throw new NotFoundException("No recipes found");
        }

        return recipeRepository.findAllByRecipePublicTrue();
    }

    public String delete(String id) throws NotFoundException {
        if (recipeRepository.findByRecipeID(id).get(0) == null) {
            throw new NotFoundException("No recipes found");
        }
        recipeRepository.deleteById(id);
        return "Deleted recipe with id: " + id;
    }
}
