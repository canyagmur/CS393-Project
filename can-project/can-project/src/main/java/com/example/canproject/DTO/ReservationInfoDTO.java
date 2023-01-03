package com.example.canproject.DTO;


import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReservationInfoDTO {
    private Long reservationNumber;
    private LocalDateTime pickUpDate;
    private LocalDateTime dropOffDate;
    private Integer pickUpLocation; //Location.code
    private Integer dropOffLocation;
    private Double totalAmount;


}