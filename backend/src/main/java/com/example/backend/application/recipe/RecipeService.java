package com.example.backend.application.recipe;

import com.example.backend.domain.image.RecipeImage;
import com.example.backend.infrastructure.persistence.image.RecipeImagesRepository;
import com.example.backend.domain.ingredient.Ingredient;
import com.example.backend.presentation.dto.IngredientDto;
import com.example.backend.infrastructure.persistence.ingredient.IngredientsRepository;
import com.example.backend.presentation.dto.RecipeCreationRequestDto;
import com.example.backend.presentation.dto.RecipeResponseDto;
import com.example.backend.domain.recipe.Recipe;
import com.example.backend.domain.recipe.RecipeIngredients;
import com.example.backend.domain.recipe.RecipeStep;
import com.example.backend.infrastructure.persistence.recipe.RecipeIngredientsRepository;
import com.example.backend.infrastructure.persistence.recipe.RecipeStepsRepository;
import com.example.backend.infrastructure.persistence.recipe.RecipesRepository;
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
public class RecipeService {

    private final IngredientsRepository ingredientsRepository;
    private final RecipeImagesRepository recipeImagesRepository;
    private final RecipeIngredientsRepository recipeIngredientsRepository;
    private final RecipesRepository recipesRepository;
    private final RecipeStepsRepository recipeStepsRepository;

    @Transactional
    public ResponseEntity<String> createRecipe(@RequestBody RecipeCreationRequestDto recipeCreationRequestDto) {
        Recipe recipe = saveRecipeInformation(recipeCreationRequestDto.getTitle(), recipeCreationRequestDto.getSteps());
        saveNewIngredients(recipeCreationRequestDto.getIngredients());
        saveRecipeIngredientsInfo(recipe, recipeCreationRequestDto.getIngredients());
        saveRecipeImagesInfo(recipe, recipeCreationRequestDto.getPictures());
        saveRecipeStepsInfo(recipe, recipeCreationRequestDto.getSteps());

        return ResponseEntity.ok("Recipe was successfully created");

    }

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

    private List<IngredientDto> getIngredients(int recipeId) {
        List<IngredientDto> ingredients = new ArrayList<>();
        Recipe recipe = recipesRepository.findById(recipeId).stream().findFirst().orElse(null);

        recipeIngredientsRepository.findAllByRecipe(recipe).forEach(ingredient -> {
            IngredientDto newIngredient = new IngredientDto();
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

    private void saveNewIngredients(List<IngredientDto> ingredientDto) {
        Set<String> existingIngredients = ingredientsRepository.findAll()
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toSet());

        Set<String> incomingIngredientNames = ingredientDto.stream()
                .map(IngredientDto::getName)
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

    private void saveRecipeIngredientsInfo(Recipe recipe, List<IngredientDto> ingredientDto) {
        List<RecipeIngredients> recipeIngredients = new ArrayList<>();
        for (IngredientDto ingredient : ingredientDto) {
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
