package com.example.backend.controller;

import com.example.backend.dto.request.RecipeCreationRequestDto;
import com.example.backend.service.RecipeCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipe/create")
public class RecipeCreationController {
    private final RecipeCreationService recipeCreationService;

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody RecipeCreationRequestDto recipeCreationRequestDto) {
        return recipeCreationService.createRecipe(recipeCreationRequestDto);
    }
}
