package com.example.canproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipmentId;
    private String name;
    private double price;

    @ManyToMany(mappedBy = "equipments")
    private List<Reservation> reservations = new ArrayList<>();

}