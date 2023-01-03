package com.example.canproject.DTO;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RentedCarsDTO {
    private String brand;
    private String model;
    private String type;
    private String transmissionType;
    private Long barcode;
    private Long reservationNumber;
    private String memberName;
    private LocalDateTime dropOffDate;
    private Integer dropOffLocation;



}
