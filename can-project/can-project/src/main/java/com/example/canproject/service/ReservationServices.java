package com.example.canproject.service;


import com.example.canproject.DTO.ExtraEquipmentDTO;
import com.example.canproject.DTO.ExtraServiceDTO;
import com.example.canproject.DTO.ReservationDTO;
import com.example.canproject.DTO.ReservationInfoDTO;
import com.example.canproject.entity.*;
import com.example.canproject.enums.CarStatus;
import com.example.canproject.enums.ReservationStatus;
import com.example.canproject.mapper.ReservationMapper;
import com.example.canproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import javax.xml.crypto.Data;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationServices {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    EquipmentRepository equipmentRepository; //should i do it with services?
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LocationRepository locationRepository;

    @Transactional(rollbackFor = {Exception.class})
    public ReservationInfoDTO save(ReservationDTO reservationDTO) throws Exception {
        Optional<Car> car = carRepository.findById(reservationDTO.getBarcode());
        Optional<Location> pickUpLocation = locationRepository.findById(reservationDTO.getPickUpLocation());
        List<Services> services = servicesRepository.findAllById(reservationDTO.getServices());
        Optional<Location> dropOffLocation = locationRepository.findById(reservationDTO.getDropOffLocation());

        Optional<Member> member = memberRepository.findById(reservationDTO.getMemberId());
        List<Equipment> equipments = equipmentRepository.findAllById(reservationDTO.getEquipments());




        if (car.isEmpty()
                || reservationDTO.getServices().stream().toList().get(0) != 0 && (reservationDTO.getServices().size() == 1 && services.size() != reservationDTO.getServices().size())
                || (reservationDTO.getEquipments().size() == 1 && reservationDTO.getEquipments().stream().toList().get(0) != 0 && equipments.size() != reservationDTO.getEquipments().size())

                || member.isEmpty() || pickUpLocation.isEmpty() || dropOffLocation.isEmpty()) {
            throw new Exception();
        } else {
            if (car.get().getStatus() != CarStatus.AVAILABLE) {
                System.out.println("[INFO]The car is not available.");
                throw new Exception();
            } else {
                car.get().setStatus(CarStatus.LOANED);
                Reservation reservation = new Reservation();
                reservation.setCar(car.get());
                reservation.setCreationDate(java.time.LocalDateTime.now());
                reservation.setEquipments(equipments);
                reservation.setPickUpDate(java.time.LocalDateTime.now().plusDays(1));
                reservation.setDropOffDate(java.time.LocalDateTime.now().plusDays(reservationDTO.getDayCount()));
                reservation.setDropOffLocation(dropOffLocation.get());
                reservation.setPickUpLocation(pickUpLocation.get());
                reservation.setServices(services);



                reservation.setMember(member.get());
                reservation.setStatus(ReservationStatus.ACTIVE);
                reservation.setReturnDate(java.time.LocalDateTime.now().plusDays(reservationDTO.getDayCount()));
                double servicesCosts = 0;
                for (Services service : services) {
                    servicesCosts += service.getPrice();
                }
                double equipmentCosts = 0;
                for (Equipment equipment : equipments) {
                    equipmentCosts += equipment.getPrice();
                }
                double carRentCost = reservationDTO.getDayCount() * car.get().getDailyPrice();
                double totalCost = servicesCosts + equipmentCosts + carRentCost;

                return ReservationMapper.INSTANCE.toReservationInfoDTO(reservationRepository.save(reservation), totalCost);
            }
        }
    }
    @Transactional(rollbackFor = {Exception.class,EmptyResultDataAccessException.class})
    public Boolean addService(ExtraServiceDTO extraServiceDTO) {
        Optional<Reservation> reservation = reservationRepository.findById(extraServiceDTO.getReservationNumber());
        Optional<Services> service = servicesRepository.findById(extraServiceDTO.getServiceId());
        if (reservation.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        } else if (service.isEmpty() || reservation.get().getServices().contains(service.get())) {
            return false;
        }
        else{
            try {
                reservation.get().getServices().add(service.get());
                reservationRepository.save(reservation.get());
                return true;
            }catch (Exception x){
                return false;
            }

        }
    }
    @Transactional(rollbackFor = {Exception.class,EmptyResultDataAccessException.class})
    public Boolean addEquipment(ExtraEquipmentDTO extraEquipmentDTO) {
        Boolean x=false;
        Optional<Reservation> reservation = reservationRepository.findById(extraEquipmentDTO.getReservationNumber());
        Optional<Equipment> equipment = equipmentRepository.findById(extraEquipmentDTO.getEquipmentId());
        if (reservation.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        } else if (equipment.isEmpty() || reservation.get().getServices().contains(equipment.get())) {
            return false;
        }
        else{
            try {
                reservation.get().getEquipments().add(equipment.get());
                reservationRepository.save(reservation.get());
                return true;
            }catch (Exception e){
                return false;
            }
        }
    }
    @Transactional(rollbackFor = {Exception.class,EmptyResultDataAccessException.class})
    public Boolean cancelReservation(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(!reservation.isPresent()){
            return false;
        }
        else{
            try {
                reservation.get().setStatus(ReservationStatus.CANCELLED);
                reservation.get().getCar().setStatus(CarStatus.AVAILABLE);
                return true;
            }catch (Exception e){
                throw e;
            }

        }

    }
}

