package com.example.backend.repository;

import com.example.backend.model.RecipeImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeImagesRepository extends JpaRepository<RecipeImage, Integer> {
}
