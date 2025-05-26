package com.example.backend.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class RecipeResponseDto {
    @NotBlank
    @Size(max = 255)
    String title;

    @NotBlank
    List<String> imagesUrl;

    @NotBlank
    List<IngredientDto> ingredients;

    @NotBlank
    int timeToCook;
}