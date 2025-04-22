package com.example.backend.service;

import com.example.backend.dto.IngredientsDto;
import com.example.backend.dto.TotalRecipeInformationDto;
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

    public TotalRecipeInformationDto getTotalRecipeInformation(String title) {
        TotalRecipeInformationDto totalRecipeInformationDto = new TotalRecipeInformationDto();

        int recipeId = recipesRepository.findAll().stream().filter(recipe -> recipe.getTitle().equals(title)).findFirst().get().getId();
        totalRecipeInformationDto.setTitle(title);
        totalRecipeInformationDto.setImagesUrl(getImagesUrls(recipeId));
        totalRecipeInformationDto.setIngredients(getIngredients(recipeId));
        totalRecipeInformationDto.setTimeToCook(getTimeToCook(recipeId));
        return totalRecipeInformationDto;
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
