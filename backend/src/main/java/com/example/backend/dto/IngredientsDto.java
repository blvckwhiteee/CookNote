package com.example.backend.dto;


import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Getter
@Setter
public class IngredientsDto {
    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    private double quantity;

    @NotBlank
    @Size(max = 255)
    private String unit;
}
