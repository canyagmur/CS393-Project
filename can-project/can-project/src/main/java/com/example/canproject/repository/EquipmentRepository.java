package com.example.canproject.repository;

import com.example.canproject.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
    @Query(nativeQuery = true,value = "select * from equipment e where e.equipment_id in :ids ")
     List<Equipment> findByEquipmentIds(@Param("ids") List<Long> ids);
}
