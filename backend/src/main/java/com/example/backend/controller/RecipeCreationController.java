package com.example.backend.controller;

import com.example.backend.dto.RecipeCreationDto;
import com.example.backend.service.RecipeCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/create")
public class RecipeCreationController {
    private final RecipeCreationService recipeCreationService;

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody RecipeCreationDto recipeCreationDto) {
        return recipeCreationService.createRecipe(recipeCreationDto);
    }
}
