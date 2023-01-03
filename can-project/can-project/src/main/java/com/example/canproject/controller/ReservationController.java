package com.example.canproject.controller;


import com.example.canproject.DTO.ExtraEquipmentDTO;
import com.example.canproject.DTO.ExtraServiceDTO;
import com.example.canproject.DTO.ReservationDTO;
import com.example.canproject.DTO.ReservationInfoDTO;
import com.example.canproject.repository.CarRepository;
import com.example.canproject.repository.EquipmentRepository;
import com.example.canproject.repository.MemberRepository;
import com.example.canproject.repository.ServicesRepository;
import com.example.canproject.service.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;



@RestController
@EnableTransactionManagement
@RequestMapping(value = "/reservationAPI")
public class ReservationController {
    @Autowired
    ReservationServices reservationServices;
    @Autowired
    CarRepository carRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    MemberRepository memberRepository;

    @PostMapping(value = "/makeNewReservation")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ReservationInfoDTO> makeNewReservation(@RequestBody ReservationDTO reservationDTO) {
        try{
            ReservationInfoDTO reservationInfoDTO=reservationServices.save(reservationDTO);
            return new ResponseEntity<>(reservationInfoDTO,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PutMapping(value="/addServiceToReservationbyId/{reservationId}")
    public ResponseEntity<Boolean> addServiceToReservationbyId(@RequestBody ExtraServiceDTO extraServiceDTO, @PathVariable Long reservationId){
        try{
            Boolean response=reservationServices.addService(extraServiceDTO);
            if(response){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
            }
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
    @PutMapping(value="/addEquipmentToReservationbyId/{reservationId}")
    public ResponseEntity<Boolean> addEquipmentToReservationbyId(@RequestBody ExtraEquipmentDTO extraEquipmentDTO, @PathVariable Long reservationId){
        try{
            Boolean response=reservationServices.addEquipment(extraEquipmentDTO);
            if(response){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
            }
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @PutMapping(value="/cancelReservationbyId/{reservationId}")
    public ResponseEntity<Boolean> cancelReservationbyId(@PathVariable Long reservationId){
        try{
            Boolean response=reservationServices.cancelReservation(reservationId);
            if(response){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
