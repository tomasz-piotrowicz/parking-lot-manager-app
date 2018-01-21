package com.cars.cars.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarDto {
    private Long id;
    private String model;
    private int year;
    private String regNumber;

}
