package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipe_steps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Column(nullable = false, unique = true)
    private int stepNumber;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int stepTime;
}
