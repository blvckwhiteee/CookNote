package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchedRecipeDto {
    private String title;
    private int matchedIngredients;
    private int timeToCook;
    private String mainPictureUrl;
}
