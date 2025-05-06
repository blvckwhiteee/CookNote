package com.example.backend.service;

import com.example.backend.model.RecipeStep;
import com.example.backend.repository.RecipeStepsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeStepsReceiveService {

    private final RecipeStepsRepository recipeStepsRepository;

    @Cacheable(value = "steps")
    public List<RecipeStep> getAllRecipeSteps(int recipeId) {
        return recipeStepsRepository.findAllByRecipe_Id(recipeId);
    }
}
