package com.senai.exec1.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookRequest (
    @NotBlank(message = "The name cannot be blank")
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters")
    String name,

    @NotBlank(message = "The author cannot be blank")
    @Size(min = 2, max = 100, message = "The author must be between 2 and 100 characters")
    String author,

    @DecimalMin(value = "0.0", message = "The price must be a positive value")
    Double price,

    @Max(value = 2025, message = "The release year must be before 2025")
    Integer releaseyear
){
    
}
