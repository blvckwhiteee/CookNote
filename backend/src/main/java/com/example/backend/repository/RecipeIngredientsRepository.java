package com.example.backend.repository;

import com.example.backend.model.Ingredient;
import com.example.backend.model.RecipeIngredients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Integer> {
    List<RecipeIngredients> getRecipeIngredientsByIngredient(Ingredient ingredient);
}
