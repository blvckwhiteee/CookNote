package com.example.backend.infrastructure.proxy;

import com.example.backend.application.ingredient.IngredientsService;
import com.example.backend.domain.ingredient.Ingredient;
import com.example.backend.infrastructure.event.EventBus;
import com.example.backend.infrastructure.logging.LogLevel;
import com.example.backend.infrastructure.logging.Loggable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IngredientProxy {
    private final EventBus eventBus;
    private final IngredientsService ingredientsService;

    @Loggable(level = LogLevel.INFO, logExecutionTime = true)
    public List<Ingredient> getAllIngredients() {
        eventBus.emit("Getting all ingredients");
        return ingredientsService.getAllIngredients();
    }
}
