package com.example.backend.presentation.recipe;

import java.util.List;

import com.example.backend.presentation.dto.RecipeCreationRequestDto;
import com.example.backend.presentation.dto.RecipeResponseDto;
import com.example.backend.domain.recipe.RecipeStep;
import com.example.backend.application.recipe.RecipeService;
import com.example.backend.application.recipe.RecipeStepsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipe/")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeStepsService recipeStepsService;
    private final RecipeService recipeService;

    @GetMapping("{recipeId}")
    public RecipeResponseDto getTotalRecipeInformation(@PathVariable int recipeId) {
        return recipeService.getTotalRecipeInformation(recipeId);
    }

    @GetMapping("{recipeId}/steps")
    public List<RecipeStep> getAllRecipeSteps(@PathVariable int recipeId) {
        return recipeStepsService.getAllRecipeSteps(recipeId);
    }

    @PostMapping("create")
    public ResponseEntity<String> createRecipe(@RequestBody RecipeCreationRequestDto recipeCreationRequestDto) {
        return recipeService.createRecipe(recipeCreationRequestDto);
    }

}