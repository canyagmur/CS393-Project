package com.example.canproject.entity;

import com.example.canproject.enums.CarStatus;
import com.example.canproject.enums.CarType;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long barcode;
    private String licensePlateNumber;
    private Integer passengerCapacity;
    private String brand;
    private String model;
    private Integer mileage;
    private String transmissionType;
    private Float dailyPrice;

    @Enumerated(EnumType.STRING)
    private CarType type;

    @Enumerated(EnumType.STRING)
    private CarStatus status;




}
