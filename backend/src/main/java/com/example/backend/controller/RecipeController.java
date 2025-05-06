package com.example.backend.controller;

import com.example.backend.dto.request.RecipeCreationRequestDto;
import com.example.backend.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping("/create")
    public ResponseEntity<String> createRecipe(@RequestBody RecipeCreationRequestDto recipeCreationRequestDto) {
        return recipeService.createRecipe(recipeCreationRequestDto);
    }

    @DeleteMapping("/delete")
    public void deleteAllRecipes() {
        recipeService.deleteAllRecipes();
    }
}
