package com.demo.drones.service;

import com.demo.drones.api.dto.DroneChargeDto;
import com.demo.drones.api.dto.DroneDto;
import com.demo.drones.api.dto.PayloadDto;
import com.demo.drones.dao.entity.Drone;
import com.demo.drones.dao.repository.DroneRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DroneService {

    private final DroneRepository droneRepository;
    private final MedicationService medicationService;
    private static final String NOT_FOUND_MESSAGE = "Drone not found: %s";
    private Long fleetSize;

    @Autowired
    public DroneService(DroneRepository droneRepository, MedicationService medicationService, @Value("${fleet.size}") Long fleetSize) {
        this.droneRepository = droneRepository;
        this.medicationService = medicationService;
        this.fleetSize = fleetSize;
    }

    public String registerDrone(DroneDto droneDto) {
        log.info("Registering drone: {}", droneDto.getSerialNumber());

        var dronesCount = droneRepository.count();
        if (dronesCount >= fleetSize) {
            log.error("Fleet size exceeded: {}", fleetSize);
            throw new IllegalStateException("Fleet size exceeded");
        }

        if (droneRepository.existsBySerialNumber(droneDto.getSerialNumber())) {
            log.error("Drone already exists: {}", droneDto.getSerialNumber());
            throw new EntityExistsException("Drone %s already exists".formatted(droneDto.getSerialNumber()));
        }

        var drone = toEntity(droneDto);
        drone = droneRepository.save(drone);
        return drone.getId();
    }

    public DroneChargeDto getBatteryPercentage(String droneId) {
        log.info("Getting battery percentage for drone: {}", droneId);

        if (!droneRepository.existsById(droneId)) {
            log.error("Drone not found: {}", droneId);
            throw new EntityExistsException("Drone %s not found".formatted(droneId));
        }

        var percentage = droneRepository.getBatteryPercentage(droneId);
        return DroneChargeDto.builder()
                .batteryPercentage(percentage)
                .build();
    }

    public List<DroneDto> getDrones(boolean availableOnly, int size, int from) {
        log.info("Getting drones");

        if (availableOnly) {
            var drones = droneRepository.findAllByStateAndBatteryCapacityGreaterThan(DroneDto.State.IDLE.name(),
                    25.0,
                    Pageable.ofSize(size).withPage(from));
            return drones.stream()
                    .map(this::toDto)
                    .toList();
        }

        var drones = droneRepository.findAll(Pageable.ofSize(size).withPage(from));
        return drones.stream()
                .map(this::toDto)
                .toList();
    }

    public DroneDto getDrone(String droneId) {
        log.info("Getting drone: {}", droneId);
        var drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(droneId)));

        return toDto(drone);
    }

    public PayloadDto getPayload(String droneId) {
        log.info("Getting payload for drone: {}", droneId);

        var drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(droneId)));

        if (drone.getMedication() == null) {
            log.error("Drone has no medication: {}", droneId);
            throw new IllegalStateException("Drone has no medication");
        }

        return PayloadDto.builder()
                .medicationName(drone.getMedication().getName())
                .medicationId(drone.getMedication().getId())
                .build();
    }

    public void loadDrone(String droneId, PayloadDto payloadDto) {
        log.info("Loading drone: {}", droneId);

        var drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(droneId)));

        var medicationWeight = medicationService.getMedicationWeight(payloadDto.getMedicationId());

        if (!isAvailableToLoad(drone, medicationWeight)) {
            log.error("Drone is not available: {}", droneId);
            throw new IllegalStateException("Drone is not available");
        }

        drone.setMedication(medicationService.getEntity(payloadDto.getMedicationId()));
        drone.setState(DroneDto.State.LOADING.name());

        droneRepository.save(drone);
    }

    public void deleteDrone(String droneId) {
        log.info("Deleting drone: {}", droneId);

        if (!droneRepository.existsById(droneId)) {
            log.error("Drone not found: {}", droneId);
            throw new EntityNotFoundException(NOT_FOUND_MESSAGE.formatted(droneId));
        }

        droneRepository.deleteById(droneId);
    }

    private boolean isAvailableToLoad(Drone drone, Double medicationWeight) {
        return drone.getState().equals(DroneDto.State.IDLE.name())
               && drone.getBatteryCapacity() >= 25.0
               && medicationWeight <= drone.getWeightLimit();
    }

    private Drone toEntity(DroneDto droneDto) {
        return Drone.builder()
                .serialNumber(droneDto.getSerialNumber())
                .model(droneDto.getModel().name())
                .weightLimit(droneDto.getWeightLimit())
                .state(droneDto.getState().name())
                .batteryCapacity(droneDto.getBatteryCapacity())
                .id(droneDto.getId())
                .build();
    }

    private DroneDto toDto(Drone drone) {
        return DroneDto.builder()
                .serialNumber(drone.getSerialNumber())
                .model(DroneDto.Model.valueOf(drone.getModel()))
                .weightLimit(drone.getWeightLimit())
                .state(DroneDto.State.valueOf(drone.getState()))
                .batteryCapacity(drone.getBatteryCapacity())
                .id(drone.getId())
                .build();
    }

}
