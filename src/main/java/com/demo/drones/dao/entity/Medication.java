package com.demo.drones.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name = "medication")
@Data
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "code", nullable = false, length = 20, unique = true)
    private String code;

    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "medication")
    private Set<Payload> payloads;
}
