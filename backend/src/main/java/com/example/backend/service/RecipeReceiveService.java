package com.example.backend.service;

import com.example.backend.dto.IngredientsDto;
import com.example.backend.dto.response.RecipeResponseDto;
import com.example.backend.model.Recipe;
import com.example.backend.repository.RecipeImagesRepository;
import com.example.backend.repository.RecipeIngredientsRepository;
import com.example.backend.repository.RecipesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeReceiveService {
    private final RecipesRepository recipesRepository;
    private final RecipeImagesRepository recipeImagesRepository;
    private final RecipeIngredientsRepository recipeIngredientsRepository;

    public RecipeResponseDto getTotalRecipeInformation(int recipeId) {
        RecipeResponseDto recipeResponseDto = new RecipeResponseDto();

        recipeResponseDto.setTitle(recipesRepository.findById(recipeId).get().getTitle());
        recipeResponseDto.setImagesUrl(getImagesUrls(recipeId));
        recipeResponseDto.setIngredients(getIngredients(recipeId));
        recipeResponseDto.setTimeToCook(getTimeToCook(recipeId));
        return recipeResponseDto;
    }

    private List<String> getImagesUrls(int recipeId) {
        List<String> imagesUrls = new ArrayList<>();
        recipeImagesRepository
                .findAll()
                .stream()
                .filter(image -> image.getRecipe().getId() == recipeId)
                .forEach(imageUrl -> imagesUrls.add(imageUrl.getImageUrl()));
        return imagesUrls;
    }

    private List<IngredientsDto> getIngredients(int recipeId) {
        List<IngredientsDto> ingredients = new ArrayList<>();
        Recipe recipe = recipesRepository.findById(recipeId).stream().findFirst().orElse(null);

        recipeIngredientsRepository.findAllByRecipe(recipe).forEach(ingredient -> {
            IngredientsDto newIngredient = new IngredientsDto();
            newIngredient.setName(ingredient.getIngredient().getName());
            newIngredient.setQuantity(ingredient.getQuantity());
            newIngredient.setUnit(ingredient.getUnit());
            ingredients.add(newIngredient);
        });
        return ingredients;
    }

    private int getTimeToCook(int recipeId) {
        return recipesRepository.findById(recipeId).get().getTotalCookingTime();
    }
}
