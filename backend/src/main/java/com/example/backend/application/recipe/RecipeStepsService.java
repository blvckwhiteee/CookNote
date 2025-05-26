package com.example.backend.domain.recipe.service;

import com.example.backend.domain.recipe.model.RecipeStep;
import com.example.backend.domain.recipe.repository.RecipeStepsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeStepsService {

    private final RecipeStepsRepository recipeStepsRepository;

    public List<RecipeStep> getAllRecipeSteps(int recipeId) {
        return recipeStepsRepository.findAllByRecipe_Id(recipeId);
    }
}
