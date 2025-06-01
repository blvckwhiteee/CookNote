package com.example.backend.infrastructure.persistence.recipe;

import com.example.backend.domain.recipe.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeStepsRepository extends JpaRepository<RecipeStep, Integer> {
    List<RecipeStep> findAllByRecipe_Id(int recipeId);
}
