package com.example.backend.repository;

import com.example.backend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipesRepository extends JpaRepository<Recipe, Integer> {
    int findByTitle(String title);
}
