package com.example.canproject.service;


import com.example.canproject.DTO.CarCreaterDTO;
import com.example.canproject.DTO.CarDTO;
import com.example.canproject.DTO.AvailableCarByTypeDTO;
import com.example.canproject.entity.Car;
import com.example.canproject.enums.CarStatus;
import com.example.canproject.mapper.CarMapper;
import com.example.canproject.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    //---------------------------GET METHODS---------------------------
    public List<CarCreaterDTO> getAllCars() {

        List<Car> cars=carRepository.findAll();
        if(cars.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        else{
            return CarMapper.INSTANCE.fromCarsToCreateCarsDTO(cars);
        }

    }
    public List<CarDTO> getAvailableCars(AvailableCarByTypeDTO availableCarByTypeDTO){
        Optional<List<Car>> cars=carRepository.getAvailableCars(availableCarByTypeDTO.getType(),
                availableCarByTypeDTO.getTransmissionType());
        if(cars.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return CarMapper.INSTANCE.toCarDTOs(cars.get());
    }
    public List<CarDTO> getAllRentedCars() {
        Optional<List<Car>> result= carRepository.getAllRentedCars();
        if(result.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return CarMapper.INSTANCE.toCarDTOs(result.get());
    }



    //---------------------------DELETE/UPDATE/SAVE METHODS---------------------------

    @Transactional(rollbackFor = {EmptyResultDataAccessException.class})
    public Boolean deleteById(long barcode) {
            Optional<Car> car = carRepository.findById(barcode);
            if(car.isPresent() ) {
                if(car.get().getStatus()!= CarStatus.AVAILABLE
                        || car.get().getStatus()== CarStatus.RESERVED){
                    return false;
                }
                else{
                    carRepository.deleteById(car.get().getBarcode());
                    return true;
                }
            }
            else{
                throw new EmptyResultDataAccessException(1);
            }

    }

    @Transactional(rollbackFor = {Exception.class})
    public CarCreaterDTO save(CarCreaterDTO carCreaterDTO) {

        try{
            Car car=CarMapper.INSTANCE.fromCreateCarDTOtoCar(carCreaterDTO);
            return CarMapper.INSTANCE.fromCarToCreateCarDTO(carRepository.save(car));
        }catch (Exception e){
            throw e;
        }

    }

    @Transactional(rollbackFor = {EmptyResultDataAccessException.class})
    public CarCreaterDTO findByBarcode(Long barcode){
        try{
            return CarMapper.INSTANCE.fromCarToCreateCarDTO(carRepository.findByBarcode(barcode));
        }catch (EmptyResultDataAccessException e){
            throw e;
        }

    }

    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public void deleteAll(){
        try{
            carRepository.deleteAll();
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

}

