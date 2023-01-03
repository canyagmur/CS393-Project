package com.example.canproject.entity;


import com.example.canproject.entity.Car;
import com.example.canproject.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reservationNumber;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    private LocalDateTime creationDate;
    private LocalDateTime pickUpDate;
    private LocalDateTime dropOffDate;

    @ManyToOne
    @JoinColumn(name = "pick_up_location_code")
    private Location pickUpLocation;

    @ManyToOne
    @JoinColumn(name = "drop_off_location_code")
    private Location dropOffLocation;
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status=ReservationStatus.NONE;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToMany
    private List<Equipment> equipments = new ArrayList<>();
    @ManyToMany
    private List<Services> services = new ArrayList<>();

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }


}
