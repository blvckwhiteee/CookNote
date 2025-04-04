package com.example.backend.repository;

import com.example.backend.model.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeStepsRepository extends JpaRepository<RecipeStep, Integer> {
}
