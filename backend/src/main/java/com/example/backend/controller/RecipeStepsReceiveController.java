package com.example.backend.controller;

import com.example.backend.model.RecipeStep;
import com.example.backend.service.RecipeStepsReceiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe/steps")
@RequiredArgsConstructor
public class RecipeStepsReceiveController {
    private final RecipeStepsReceiveService recipeStepsReceiveService;

    @GetMapping("/{title}")
    public List<RecipeStep> getAllRecipeSteps(@PathVariable String title) {
        return recipeStepsReceiveService.getAllRecipeSteps(title);
    }
}
