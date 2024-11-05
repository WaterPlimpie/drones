package com.demo.drones.api.controllers;

import com.demo.drones.api.MedicationApi;
import com.demo.drones.api.dto.MedicationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicationApiController implements MedicationApi {
    @Override
    public ResponseEntity<Void> registerMedication(MedicationDto medicationDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<MedicationDto>> getMedications(int size, int from) {
        return null;
    }

    @Override
    public ResponseEntity<MedicationDto> getMedication(String medicationId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateMedication(String medicationId, MedicationDto medicationDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteMedication(String medicationId) {
        return null;
    }
}
