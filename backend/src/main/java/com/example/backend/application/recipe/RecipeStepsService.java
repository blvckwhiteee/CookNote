package com.example.backend.application.recipe;

import com.example.backend.domain.recipe.RecipeStep;
import com.example.backend.infrastructure.persistence.recipe.RecipeStepsRepository;
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
