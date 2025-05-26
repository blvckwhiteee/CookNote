package com.example.backend.domain.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientsRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByNameInIgnoreCase(List<String> names);
}
