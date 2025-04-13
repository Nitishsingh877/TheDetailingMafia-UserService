package com.thederailingmafia.carwash.user_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private String brand;
    private String model;
    private String licenceNumberPlate;

}
