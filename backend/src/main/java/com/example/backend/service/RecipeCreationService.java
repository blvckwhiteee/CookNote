package com.example.backend.service;

import com.example.backend.dto.IngredientsDto;
import com.example.backend.dto.RecipeCreationDto;
import com.example.backend.model.*;
import com.example.backend.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RecipeCreationService {

    private final IngredientsRepository ingredientsRepository;
    private final RecipeImagesRepository recipeImagesRepository;
    private final RecipeIngredientsRepository recipeIngredientsRepository;
    private final RecipesRepository recipesRepository;
    private final RecipeStepsRepository recipeStepsRepository;

    @Transactional
    public ResponseEntity<String> createRecipe(@RequestBody RecipeCreationDto recipeCreationDto) {
        Recipe recipe = saveRecipeInformation(recipeCreationDto.getTitle(), recipeCreationDto.getSteps());
        saveNewIngredients(recipeCreationDto.getIngredients());
        saveRecipeIngredientsInfo(recipe, recipeCreationDto.getIngredients());
        saveRecipeImagesInfo(recipe, recipeCreationDto.getPictures());
        saveRecipeStepsInfo(recipe, recipeCreationDto.getSteps());

        return ResponseEntity.ok("Recipe was successfully created");

    }

    private Recipe saveRecipeInformation(String title, List<RecipeStep> steps) {
        Recipe recipe = new Recipe();
        recipe.setTitle(title);
        int totalTime = 0;
        for (RecipeStep step : steps) {
            totalTime += step.getStepTime();
        }
        recipe.setTotalCookingTime(totalTime);
        recipesRepository.save(recipe);
        return recipe;
    }

    private void saveNewIngredients(List<IngredientsDto> ingredientsDto) {
        Set<String> existingIngredients = ingredientsRepository.findAll()
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toSet());

        Set<String> incomingIngredientNames = ingredientsDto.stream()
                .map(IngredientsDto::getName)
                .collect(Collectors.toSet());

        Set<String> newIngredientNames = incomingIngredientNames.stream()
                .filter(name -> !existingIngredients.contains(name))
                .collect(Collectors.toSet());

        List<Ingredient> newIngredients = newIngredientNames.stream()
                .map(name -> {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setName(name);
                    return ingredient;
                })
                .collect(Collectors.toList());

        ingredientsRepository.saveAll(newIngredients);
    }

    private void saveRecipeIngredientsInfo(Recipe recipe, List<IngredientsDto> ingredientsDto) {
        List<RecipeIngredients> recipeIngredients = new ArrayList<>();
        for (IngredientsDto ingredient : ingredientsDto) {
            RecipeIngredients recipeIngredient = new RecipeIngredients();
            recipeIngredient.setIngredient(
                    ingredientsRepository
                            .findAll()
                            .stream()
                            .filter(existingIngredient -> existingIngredient.getName().equals(ingredient.getName()))
                            .findFirst()
                            .get()
            );


            recipeIngredient.setRecipe(recipe);

            recipeIngredient.setUnit(ingredient.getUnit());
            recipeIngredient.setQuantity(ingredient.getQuantity());

            recipeIngredients.add(recipeIngredient);
        }
        recipeIngredientsRepository.saveAll(recipeIngredients);

    }

    private void saveRecipeImagesInfo(Recipe recipe, List<RecipeImage> recipeImages) {
        List<RecipeImage> recipeImageList = new ArrayList<>();
        for (RecipeImage recipeImage : recipeImages) {
            RecipeImage recipeImageInfo = new RecipeImage();
            recipeImageInfo.setRecipe(recipe);
            recipeImageInfo.setImageUrl(recipeImage.getImageUrl());
            recipeImageInfo.setIsMain(recipeImage.getIsMain());
            recipeImageList.add(recipeImageInfo);
        }
        recipeImagesRepository.saveAll(recipeImageList);
    }

    private void saveRecipeStepsInfo(Recipe recipe, List<RecipeStep> recipeSteps) {
        List<RecipeStep> recipeStepList = new ArrayList<>();
        for (RecipeStep recipeStep : recipeSteps) {
            RecipeStep recipeStepInfo = new RecipeStep();
            recipeStepInfo.setRecipe(recipe);
            recipeStepInfo.setStepTime(recipeStep.getStepTime());
            recipeStepInfo.setStepNumber(recipeStep.getStepNumber());
            recipeStepInfo.setDescription(recipeStep.getDescription());
            recipeStepList.add(recipeStepInfo);
        }
        recipeStepsRepository.saveAll(recipeStepList);
    }

}
