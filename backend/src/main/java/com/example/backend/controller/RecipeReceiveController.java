package com.example.backend.controller;

import com.example.backend.dto.TotalRecipeInformationDto;
import com.example.backend.service.RecipeReceiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
public class RecipeReceiveController {
    private final RecipeReceiveService recipeReceiveService;

    @GetMapping("{title}")
    public TotalRecipeInformationDto getTotalRecipeInformation(@PathVariable String title) {
        return recipeReceiveService.getTotalRecipeInformation(title);
    }
}
