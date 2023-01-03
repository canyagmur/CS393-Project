package com.example.canproject.mapper;



import com.example.canproject.DTO.CarCreaterDTO;
import com.example.canproject.DTO.CarDTO;
import com.example.canproject.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarMapper INSTANCE= Mappers.getMapper(CarMapper.class);
    CarDTO toCarDTO(Car car);
    Car toCar(CarDTO carDTO);
    List<CarDTO> toCarDTOs(List<Car> cars);
    List<Car> toCars(List<CarDTO> CarDTO);

    CarCreaterDTO fromCarToCreateCarDTO(Car car);
    Car fromCreateCarDTOtoCar(CarCreaterDTO carCreaterDTO);

    List<CarCreaterDTO> fromCarsToCreateCarsDTO(List<Car> cars);
    List<Car> fromCreateCarDTOSToCars(List<CarCreaterDTO> carCreaterDTOS);
}