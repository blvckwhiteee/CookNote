package com.example.backend.presentation.ingredient;

import com.example.backend.application.ingredient.IngredientsService;
import com.example.backend.domain.ingredient.Ingredient;
import com.example.backend.presentation.dto.MatchedRecipeResponseDto;
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
