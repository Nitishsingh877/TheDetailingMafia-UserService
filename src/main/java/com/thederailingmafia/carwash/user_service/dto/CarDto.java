package com.thederailingmafia.carwash.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    @NotBlank(message = "Brand cannot be empty")
    @Size(min = 2, max = 50, message = "Brand name must be between 2 and 50 characters")
    private String brand;

    @NotBlank(message = "Model cannot be empty")
    @Size(min = 1, max = 50, message = "Model name must be between 1 and 50 characters")
    private String model;

    @NotBlank(message = "License plate cannot be empty")
    @Pattern(regexp = "^[A-Z0-9-]{6,10}$", message = "Invalid license plate format")
    private String licenceNumberPlate;
}