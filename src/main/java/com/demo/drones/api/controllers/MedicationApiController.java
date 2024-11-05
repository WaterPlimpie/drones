package com.demo.drones.api.controllers;

import com.demo.drones.api.MedicationApi;
import com.demo.drones.api.dto.MedicationDto;
import com.demo.drones.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static com.demo.drones.api.ApiConstants.BASE_PATH;

@RestController
public class MedicationApiController implements MedicationApi {

    private final MedicationService medicationService;

    @Autowired
    public MedicationApiController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @Override
    public ResponseEntity<Void> registerMedication(MedicationDto medicationDto) {

        var medicationId = medicationService.registerMedication(medicationDto);
        return ResponseEntity.created(URI.create(BASE_PATH + "/medications/" + medicationId)).build();
    }

    @Override
    public ResponseEntity<List<MedicationDto>> getMedications(int size, int from) {
        var medications = medicationService.getMedications(size, from);
        return ResponseEntity.ok(medications);
    }

    @Override
    public ResponseEntity<MedicationDto> getMedication(String medicationId) {
        var medication = medicationService.getMedication(medicationId);
        return ResponseEntity.ok(medication);
    }

    @Override
    public ResponseEntity<Void> updateMedication(String medicationId, MedicationDto medicationDto) {
        medicationService.updateMedication(medicationId, medicationDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteMedication(String medicationId) {
        medicationService.deleteMedication(medicationId);
        return ResponseEntity.noContent().build();
    }
}
