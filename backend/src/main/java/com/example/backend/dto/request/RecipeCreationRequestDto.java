package com.example.backend.dto.request;

import com.example.backend.dto.IngredientsDto;
import com.example.backend.model.*;
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
    private List<IngredientsDto> ingredients;

    @NotBlank
    private List<RecipeImage> pictures;

    @NotBlank
    private List<RecipeStep> steps;
}
