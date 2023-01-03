package com.example.canproject.DTO;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReservationDTO {

    private Long barcode;
    private int dayCount;
    private Long memberId;
    private Integer pickUpLocation;
    private Integer dropOffLocation;
    private Set<Long> equipments;
    private Set<Long> services;


}