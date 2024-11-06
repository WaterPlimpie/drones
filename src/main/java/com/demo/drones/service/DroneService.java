package com.demo.drones.service;

import com.demo.drones.api.dto.DroneChargeDto;
import com.demo.drones.api.dto.DroneDto;
import com.demo.drones.api.dto.PayloadDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DroneService {

    public String registerDrone(DroneDto droneDto) {
        log.info("Registering drone: {}", droneDto.getSerialNumber());
        // return dummy id
        return "123";
    }

    public DroneChargeDto getBatteryPercentage(String droneId) {
        log.info("Getting battery percentage for drone: {}", droneId);
        // return dummy charge
        return DroneChargeDto.builder()
                .droneId(droneId)
                .batteryPercentage(100.0)
                .build();
    }

    public List<DroneDto> getDrones(boolean available, int size, int from) {
        log.info("Getting drones");
        // return dummy drones
        return List.of(
                DroneDto.builder()
                        .serialNumber("123")
                        .build(),
                DroneDto.builder()
                        .serialNumber("456")
                        .build()
        );
    }

    public DroneDto getDrone(String droneId) {
        log.info("Getting drone: {}", droneId);
        // return dummy drone
        return DroneDto.builder()
                .serialNumber(droneId)
                .build();
    }

    public PayloadDto getPayload(String droneId) {
        log.info("Getting payload for drone: {}", droneId);
        // return dummy payload
        return PayloadDto.builder()
                .medications(List.of(
                        PayloadDto.Item.builder()
                                .medicationId("123")
                                .count(1)
                                .build())
                ).build();
    }

    public void loadDrone(String droneId, PayloadDto payloadDto) {
        log.info("Loading drone: {}", droneId);
    }
}
