package com.example.canproject.DTO;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CarCreaterDTO {
    private Long barcode;
    private String licensePlateNumber;
    private Integer passengerCapacity;
    private String brand;
    private String model;
    private Integer mileage;
    private String transmissionType;
    private Float dailyPrice;
    private String type;
    private String status;

}
