package com.example.backend.controller;

import com.example.backend.dto.MatchedRecipeDto;
import com.example.backend.model.Ingredient;
import com.example.backend.service.IngredientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<MatchedRecipeDto> getAllMatchedRecipes(@RequestBody List<String> ingredientNames) {
        return ingredientsService.getAllMatchedRecipesByNames(ingredientNames);
    }
}
