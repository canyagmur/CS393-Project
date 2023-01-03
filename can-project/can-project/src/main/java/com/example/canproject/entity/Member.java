package com.example.canproject.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String phone;
    private String drivingLicenseNumber;

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations;

}