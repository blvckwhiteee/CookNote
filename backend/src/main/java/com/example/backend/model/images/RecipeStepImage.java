package com.example.backend.model.images;

import com.example.backend.model.RecipeStep;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipe_step_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeStepImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "step_id", nullable = false)
    private RecipeStep step;

    @Column(nullable = false, unique = true)
    private String imageUrl;
}
