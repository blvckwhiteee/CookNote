package com.example.backend.infrastructure.persistence.ingredient;

import com.example.backend.domain.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientsRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByNameInIgnoreCase(List<String> names);
}
