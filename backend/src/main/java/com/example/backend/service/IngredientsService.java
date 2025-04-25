package com.example.backend.service;

import com.example.backend.dto.response.MatchedRecipeResponseDto;
import com.example.backend.model.Ingredient;
import com.example.backend.model.Recipe;
import com.example.backend.model.RecipeIngredients;
import com.example.backend.repository.IngredientsRepository;
import com.example.backend.repository.RecipeImagesRepository;
import com.example.backend.repository.RecipeIngredientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientsService {
    private final IngredientsRepository ingredientsRepository;
    private final RecipeIngredientsRepository recipeIngredientsRepository;
    private final RecipeImagesRepository recipeImagesRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientsRepository.findAll();
    }

    public List<MatchedRecipeResponseDto> getAllMatchedRecipesByNames(List<String> ingredientNames) {
        if (ingredientNames == null || ingredientNames.isEmpty()) {
            return Collections.emptyList();
        }

        List<Ingredient> userIngredients = ingredientsRepository.findByNameInIgnoreCase(ingredientNames);
        Set<Integer> userIngredientIds = userIngredients.stream()
                .map(Ingredient::getId)
                .collect(Collectors.toSet());

        if (userIngredientIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<RecipeIngredients> allRecipeIngredients = recipeIngredientsRepository.findAll();

        Map<Recipe, List<RecipeIngredients>> ingredientsByRecipe = allRecipeIngredients.stream()
                .collect(Collectors.groupingBy(RecipeIngredients::getRecipe));

        List<MatchedRecipeResponseDto> matchedRecipes = new ArrayList<>();

        for (Map.Entry<Recipe, List<RecipeIngredients>> entry : ingredientsByRecipe.entrySet()) {
            Recipe recipe = entry.getKey();
            List<RecipeIngredients> recipeIngredients = entry.getValue();

            long matchedCount = recipeIngredients.stream()
                    .filter(ri -> userIngredientIds.contains(ri.getIngredient().getId()))
                    .count();

            if (matchedCount > 0) {
                MatchedRecipeResponseDto dto = new MatchedRecipeResponseDto();
                dto.setTitle(recipe.getTitle());
                dto.setMatchedIngredients((int) matchedCount);
                dto.setTimeToCook(recipe.getTotalCookingTime());

                recipeImagesRepository.findByRecipeIdAndIsMainTrue(recipe.getId())
                        .ifPresent(image -> dto.setMainPictureUrl(image.getImageUrl()));

                matchedRecipes.add(dto);
            }
        }

        return matchedRecipes;
    }
}
