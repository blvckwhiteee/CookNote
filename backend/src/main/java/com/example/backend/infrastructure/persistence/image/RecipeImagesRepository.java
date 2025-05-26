package com.example.backend.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeImagesRepository extends JpaRepository<RecipeImage, Integer> {
    Optional<RecipeImage> findByRecipeIdAndIsMainTrue(Integer recipeId);

    List<RecipeImage> findAllById(int id);
}
