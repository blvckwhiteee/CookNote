package com.example.backend.model.images;

import com.example.backend.model.Recipe;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipe_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Column(nullable = false, unique = true)
    private String imageUrl;

    @Column(nullable = false)
    private boolean isMain;
}
