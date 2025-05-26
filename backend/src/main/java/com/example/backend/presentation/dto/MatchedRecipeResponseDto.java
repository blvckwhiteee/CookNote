package com.example.backend.domain.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Getter
@Setter
public class MatchedRecipeResponseDto {
    @NotBlank
    @Size(max = 255)
    private String title;

    @NotBlank
    private int id;

    @NotBlank
    private int matchedIngredients;

    @NotBlank
    private int timeToCook;

    @NotBlank
    @Size(max = 255)
    private String mainPictureUrl;
}
