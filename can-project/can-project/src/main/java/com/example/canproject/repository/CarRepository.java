package com.example.canproject.repository;

import com.example.canproject.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    @Query(value = "select * from Car c where c.status='AVAILABLE' and c.type=?1 and c.transmission_type=?2", nativeQuery = true)
    Optional<List<Car>> getAvailableCars(String type, String transmission);

    @Query(value = "select * from Car c where c.status='LOANED' or c.status='RESERVED'", nativeQuery = true)
    Optional<List<Car>> getAllRentedCars();
    Car findByBarcode(long barcode);
    Car save(Car car);

}
