package com.demo.drones.service;

import com.demo.drones.api.dto.MedicationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MedicationService {

    public String registerMedication(MedicationDto medicationDto) {
        log.info("Registering medication: {}", medicationDto.getName());
        // return dummy id
        return "123";
    }

    public List<MedicationDto> getMedications(int size, int from) {
        log.info("Getting medications");
        // return dummy medications
        return List.of(
                MedicationDto.builder()
                        .name("Medication 1")
                        .build(),
                MedicationDto.builder()
                        .name("Medication 2")
                        .build()
        );
    }

    public MedicationDto getMedication(String medicationId) {
        log.info("Getting medication: {}", medicationId);
        // return dummy medication
        return MedicationDto.builder()
                .name("Medication 1")
                .build();
    }

    public void updateMedication(String medicationId, MedicationDto medicationDto) {
        log.info("Updating medication: {}", medicationId);
    }

    public void deleteMedication(String medicationId) {
        log.info("Deleting medication: {}", medicationId);
    }
}
