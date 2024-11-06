package com.demo.drones.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name = "drone")
@Data
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "serial_number", nullable = false, length = 100)
    private String serialNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "weight_limit")
    private Double weightLimit;

    @Column(name = "battery_capacity")
    private Double batteryCapacity;

    @Column(name = "state")
    private String state;

    @OneToMany(mappedBy = "drone")
    private Set<Payload> payloads;
}
