package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TotalRecipeInformationDto {
    String title;
    List<String> imagesUrl;
    List<IngredientsDto> ingredients;
    int timeToCook;
}