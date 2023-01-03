package com.example.canproject.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Location {
    //TODO: Are we using name and code as id, or just name?
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int code;
    private String name;
    private String address;


}