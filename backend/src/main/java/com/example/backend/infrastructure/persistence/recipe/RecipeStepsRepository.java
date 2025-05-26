package com.example.backend.domain.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeStepsRepository extends JpaRepository<RecipeStep, Integer> {
    List<RecipeStep> findAllByRecipe_Id(int recipeId);
}
