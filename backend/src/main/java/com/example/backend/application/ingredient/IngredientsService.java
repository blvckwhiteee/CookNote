package com.example.backend.application.ingredient;

import com.example.backend.domain.ingredient.Ingredient;
import com.example.backend.infrastructure.event.EventBus;
import com.example.backend.infrastructure.persistence.ingredient.IngredientsRepository;
import com.example.backend.domain.recipe.Recipe;
import com.example.backend.presentation.dto.MatchedRecipeResponseDto;
import com.example.backend.domain.recipe.RecipeIngredients;
import com.example.backend.infrastructure.persistence.image.RecipeImagesRepository;
import com.example.backend.infrastructure.persistence.recipe.RecipeIngredientsRepository;
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
                dto.setId(recipe.getId());

                recipeImagesRepository.findByRecipeIdAndIsMainTrue(recipe.getId())
                        .ifPresent(image -> dto.setMainPictureUrl(image.getImageUrl()));

                matchedRecipes.add(dto);
            }
        }

        return matchedRecipes;
    }
}
