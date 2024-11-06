package com.demo.drones.service;

import com.demo.drones.api.dto.MedicationDto;
import com.demo.drones.dao.entity.Medication;
import com.demo.drones.dao.repository.MedicationRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class MedicationService {

    private final MedicationRepository medicationRepository;

    private static final String NOT_FOUND_MESSAGE = "Medication not found: %s";

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public String registerMedication(MedicationDto medicationDto) {

        if (medicationRepository.existsByCode(medicationDto.getCode())) {
            log.error("Medication already exists: {}", medicationDto.getCode());
            throw new EntityExistsException("Medication %s already exists".formatted(medicationDto.getCode()));
        }

        // save medication
        log.info("Registering medication: {}", medicationDto.getCode());

        var medication = toEntity(medicationDto);
        medication = medicationRepository.save(medication);
        return medication.getId();
    }

    public List<MedicationDto> getMedications(int size, int from) {
        log.info("Getting medications: size={}, from={}", size, from);
        var medications = medicationRepository.findAll(Pageable.ofSize(size).withPage(from));
        return medications.stream()
                .map(this::toDto)
                .toList();
    }

    public MedicationDto getMedication(String medicationId) {
        log.info("Getting medication: {}", medicationId);
        var medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(medicationId)));

        return toDto(medication);
    }

    public void updateMedication(String medicationId, MedicationDto medicationDto) {
        log.info("Updating medication: {}", medicationId);

        if (!medicationRepository.existsById(medicationId)) {
            throw new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(medicationId));
        }

        var medication = toEntity(medicationDto);
        medication.setId(medicationId);
        medicationRepository.save(medication);
    }

    public void deleteMedication(String medicationId) {
        log.info("Deleting medication: {}", medicationId);

        if (!medicationRepository.existsById(medicationId)) {
            throw new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(medicationId));
        }
        medicationRepository.deleteById(medicationId);
    }

    protected Double getMedicationWeight(String medicationId) {
        log.info("Getting medication weight: {}", medicationId);

        if (!medicationRepository.existsById(medicationId)) {
            throw new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(medicationId));
        }
        return medicationRepository.getWeightById(medicationId);
    }

    protected Medication getEntity(String medicationId) {
        return medicationRepository.findById(medicationId)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(medicationId)));
    }

    private MedicationDto toDto(Medication medication) {
        return MedicationDto.builder()
                .code(medication.getCode())
                .name(medication.getName())
                .weight(medication.getWeight())
                .id(medication.getId())
                .build();
    }

    private Medication toEntity(MedicationDto medicationDto) {
        var builder = Medication.builder()
                .code(medicationDto.getCode())
                .name(medicationDto.getName())
                .weight(medicationDto.getWeight());

        if (medicationDto.getImage() != null) {
            try {
                byte[] image = medicationDto.getImage().getBytes();
                builder.image(image);
            } catch (IOException e) {
                log.warn("Failed to read image", e);
            }
        }
        return builder.build();
    }
}
