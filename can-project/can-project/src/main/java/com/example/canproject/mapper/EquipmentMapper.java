package com.example.canproject.mapper;


import com.example.canproject.DTO.EquipmentDTO;
import com.example.canproject.entity.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    EquipmentMapper INSTANCE= Mappers.getMapper(EquipmentMapper.class);

    List<EquipmentDTO> toEquipmentDTOS(List<Equipment> equipments);
    List<Equipment> toEquipment(List<EquipmentDTO> equipmentDTOS);
}
