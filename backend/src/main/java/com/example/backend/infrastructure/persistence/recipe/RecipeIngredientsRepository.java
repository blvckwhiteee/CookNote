package com.example.backend.domain.recipe;

import com.example.backend.domain.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Integer> {
    List<RecipeIngredients> getRecipeIngredientsByIngredient(Ingredient ingredient);

    List<RecipeIngredients> findAllByRecipe(Recipe recipe);
}
