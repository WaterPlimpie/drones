package com.demo.drones.api.controllers;

import com.demo.drones.api.DroneApi;
import com.demo.drones.api.dto.DroneChargeDto;
import com.demo.drones.api.dto.DroneDto;
import com.demo.drones.api.dto.PayloadDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DroneApiController implements DroneApi {

    @Override
    public ResponseEntity<Void> registerDrone(DroneDto droneDto) {
        return null;
    }

    @Override
    public ResponseEntity<DroneChargeDto> getBatteryPercentage(String droneId) {
        return null;
    }

    @Override
    public ResponseEntity<List<DroneDto>> getDrones(boolean available, int size, int from) {
        return null;
    }

    @Override
    public ResponseEntity<DroneDto> getDrone(String droneId) {
        return null;
    }

    @Override
    public ResponseEntity<PayloadDto> getPayload(String droneId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> loadDrone(String droneId, PayloadDto payloadDto) {
        return null;
    }
}
