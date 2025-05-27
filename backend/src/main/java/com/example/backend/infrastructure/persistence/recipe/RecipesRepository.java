package com.example.backend.infrastructure.persistence.recipe;

import com.example.backend.domain.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipesRepository extends JpaRepository<Recipe, Integer> {
    int findByTitle(String title);
}
