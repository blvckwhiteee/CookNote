package com.example.backend.domain.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipesRepository extends JpaRepository<Recipe, Integer> {
    int findByTitle(String title);
}
