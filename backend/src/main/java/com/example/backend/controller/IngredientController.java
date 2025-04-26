package com.example.backend.controller;

import com.example.backend.dto.response.MatchedRecipeResponseDto;
import com.example.backend.model.Ingredient;
import com.example.backend.service.IngredientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/api/ingredients")
public class IngredientController {
    private final IngredientsService ingredientsService;

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientsService.getAllIngredients();
    }


    @GetMapping("/search")
    public List<MatchedRecipeResponseDto> getAllMatchedRecipes(@RequestParam List<String> ingredientNames) {
        return ingredientsService.getAllMatchedRecipesByNames(ingredientNames);
    }
}
