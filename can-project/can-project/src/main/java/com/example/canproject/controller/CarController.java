package com.example.canproject.controller;





import com.example.canproject.DTO.CarCreaterDTO;
import com.example.canproject.DTO.CarDTO;
import com.example.canproject.DTO.AvailableCarByTypeDTO;
import com.example.canproject.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/carAPI")
public class CarController {
    @Autowired
    CarService carService;

    //---------------------------GET METHODS---------------------------
    @GetMapping(value="/getAllCars")
    public ResponseEntity<List<CarCreaterDTO>> getAllCars(){
        try {
            List<CarCreaterDTO> cars = carService.getAllCars();
            return new ResponseEntity<>(cars,HttpStatus.OK);
        }
        catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value="/getAvailableCars",method= RequestMethod.GET)
    public ResponseEntity<List<CarDTO>> getAvailableCars(@io.swagger.v3.oas.annotations.parameters.RequestBody AvailableCarByTypeDTO availableCarByTypeDTO){
        try {
            List<CarDTO> carDTOS = carService.getAvailableCars(availableCarByTypeDTO);
            return new ResponseEntity<>(carDTOS,HttpStatus.OK);
        }
       catch(EmptyResultDataAccessException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
    @GetMapping(value="/getAllRentedCars")
    public ResponseEntity<List<CarDTO>> getAllRentedCars() {

        try{
            List<CarDTO> carDTOS = carService.getAllRentedCars();
            return new ResponseEntity<>(carDTOS, HttpStatus.OK);
        } catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //---------------------------DELETE/UPDATE/SAVE METHODS---------------------------
    @DeleteMapping(value="/deleteCarByBarcode/{id}")
    public ResponseEntity<Boolean> deleteCarByBarcode(@PathVariable (name="id",required = false) Long barcode){
        try {
            Boolean response = carService.deleteById(barcode);
            if(response){
                return new ResponseEntity(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }catch (Exception e){
            if(e.getClass()==EmptyResultDataAccessException.class) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping(value="/updateCarById/{id}")
    public ResponseEntity<CarCreaterDTO> updateCarById(@RequestBody CarCreaterDTO carCreaterDTO, @PathVariable(value = "id") int id ){
        try {
            carService.save(carCreaterDTO);
            return new ResponseEntity<>(carCreaterDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value="/saveNewCar",method = RequestMethod.POST)
    public ResponseEntity<CarCreaterDTO> saveNewCar(@RequestBody CarCreaterDTO carCreaterDTO){
        try {
            CarCreaterDTO response=carService.save(carCreaterDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }



    @GetMapping(value="/findCarByBarcode/{id}")
    public ResponseEntity<CarCreaterDTO> findCarByBarcode(@RequestParam(name="id",required = false) Long barcode) {
            try {
                CarCreaterDTO carCreaterDTO = carService.findByBarcode(barcode);
                return new ResponseEntity(carCreaterDTO, HttpStatus.OK);
            }catch(EmptyResultDataAccessException e){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
}

