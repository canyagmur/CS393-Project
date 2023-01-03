package com.example.canproject.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CarDTO {
    private Long barcode;
    private String brand;
    private String model;
    private Integer mileage;
    private String transmissionType;
    private String type;


}