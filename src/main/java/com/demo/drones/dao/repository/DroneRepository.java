package com.demo.drones.dao.repository;

import com.demo.drones.dao.entity.Drone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {
    boolean existsBySerialNumber(String serialNumber);

    @Query("SELECT d.batteryCapacity FROM drone d WHERE d.id = :droneId")
    Double getBatteryPercentage(String droneId);

    Page<Drone> findAllByStateAndBatteryCapacityGreaterThan(String state, double batteryCapacity, Pageable pageable);
}
