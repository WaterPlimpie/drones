package com.demo.drones.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "payload")
@Data
public class Payload {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false, referencedColumnName = "id")
    private Drone drone;

    @ManyToOne
    @JoinColumn(name = "medication_id", nullable = false, referencedColumnName = "id")
    private Medication medication;

    @Column(name = "count", nullable = false)
    private Integer count;
}
