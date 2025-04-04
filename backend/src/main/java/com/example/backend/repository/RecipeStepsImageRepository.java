package com.example.backend.repository;

import com.example.backend.model.images.RecipeStepImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeStepsImageRepository extends JpaRepository<RecipeStepImage, Integer> {
}
