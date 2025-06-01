package com.example.backend.presentation.dto;

import com.example.backend.domain.image.RecipeImage;
import com.example.backend.domain.recipe.RecipeStep;
import lombok.*;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class RecipeCreationRequestDto {
    @NotBlank
    @Size(max = 255)
    private String title;

    @NotBlank
    private List<IngredientDto> ingredients;

    @NotBlank
    private List<RecipeImage> pictures;

    @NotBlank
    private List<RecipeStep> steps;
}
