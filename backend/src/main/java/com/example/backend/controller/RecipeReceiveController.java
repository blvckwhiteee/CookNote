package com.example.backend.controller;

import com.example.backend.dto.response.RecipeResponseDto;
import com.example.backend.model.RecipeStep;
import com.example.backend.service.RecipeReceiveService;
import com.example.backend.service.RecipeStepsReceiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe/{recipeId}")
@RequiredArgsConstructor
public class RecipeReceiveController {
    private final RecipeReceiveService recipeReceiveService;
    private final RecipeStepsReceiveService recipeStepsReceiveService;

    @GetMapping
    public RecipeResponseDto getTotalRecipeInformation(@PathVariable int recipeId) {
        return recipeReceiveService.getTotalRecipeInformation(recipeId);
    }

    @GetMapping("/steps")
    public List<RecipeStep> getAllRecipeSteps(@PathVariable int recipeId) {
        return recipeStepsReceiveService.getAllRecipeSteps(recipeId);
    }

}
