package com.example.backend.repository;

import com.example.backend.model.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeStepsRepository extends JpaRepository<RecipeStep, Integer> {
    List<RecipeStep> findAllByRecipe_Title(String recipeTitle);
}
