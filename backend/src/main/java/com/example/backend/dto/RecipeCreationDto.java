package com.example.backend.dto;

import com.example.backend.model.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class RecipeCreationDto {
    private String title;
    private List<IngredientsDto> ingredients;
    private List<RecipeImage> pictures;
    private List<RecipeStep> steps;
}
