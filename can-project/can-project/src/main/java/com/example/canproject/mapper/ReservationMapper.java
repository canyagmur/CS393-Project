package com.example.canproject.mapper;


import com.example.canproject.DTO.ReservationInfoDTO;
import com.example.canproject.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationMapper INSTANCE= Mappers.getMapper(ReservationMapper.class);

    @Mapping(source="reservation.pickUpLocation.code",target="pickUpLocation")
    @Mapping(source="reservation.dropOffLocation.code",target="dropOffLocation")
    @Mapping(source="totalAmount",target="totalAmount")
    ReservationInfoDTO toReservationInfoDTO(Reservation reservation, double totalAmount);
}
