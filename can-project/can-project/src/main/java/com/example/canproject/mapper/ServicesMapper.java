package com.example.canproject.mapper;


import com.example.canproject.DTO.ServicesDTO;
import com.example.canproject.entity.Services;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicesMapper {
    ServicesMapper INSTANCE= Mappers.getMapper(ServicesMapper.class);

    List<ServicesDTO> toServicesDTOS(List<Services> services);
}
