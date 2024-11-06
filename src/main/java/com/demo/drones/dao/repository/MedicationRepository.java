package com.demo.drones.dao.repository;

import com.demo.drones.dao.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String> {

    boolean existsByCode(String code);

    @Query("SELECT m.weight FROM medication m WHERE m.id = :id")
    double getWeightById(String id);
}
