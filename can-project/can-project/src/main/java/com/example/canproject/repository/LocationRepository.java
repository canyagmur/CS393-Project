package com.example.canproject.repository;

import com.example.canproject.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {
    Location findByCode(int code);
}
