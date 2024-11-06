package com.demo.drones.api.controllers;

import com.demo.drones.api.DroneApi;
import com.demo.drones.api.dto.DroneChargeDto;
import com.demo.drones.api.dto.DroneDto;
import com.demo.drones.api.dto.PayloadDto;
import com.demo.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static com.demo.drones.api.ApiConstants.BASE_PATH;

@RestController
public class DroneApiController implements DroneApi {

    private final DroneService droneService;

    @Autowired
    public DroneApiController(DroneService droneService) {
        this.droneService = droneService;
    }

    @Override
    public ResponseEntity<Void> registerDrone(DroneDto droneDto) {

        var droneId = droneService.registerDrone(droneDto);
        return ResponseEntity.created(URI.create(BASE_PATH + "/drones/" + droneId)).build();
    }

    @Override
    public ResponseEntity<DroneChargeDto> getBatteryPercentage(String droneId) {
        var batteryPercentage = droneService.getBatteryPercentage(droneId);
        return ResponseEntity.ok(batteryPercentage);
    }

    @Override
    public ResponseEntity<List<DroneDto>> getDrones(boolean availableOnly, int size, int from) {
        var drones = droneService.getDrones(availableOnly, size, from);
        return ResponseEntity.ok(drones);
    }

    @Override
    public ResponseEntity<DroneDto> getDrone(String droneId) {
        var drone = droneService.getDrone(droneId);
        return ResponseEntity.ok(drone);
    }

    @Override
    public ResponseEntity<PayloadDto> getPayload(String droneId) {
        var payload = droneService.getPayload(droneId);
        return ResponseEntity.ok(payload);
    }

    @Override
    public ResponseEntity<Void> loadDrone(String droneId, PayloadDto payloadDto) {
        droneService.loadDrone(droneId, payloadDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteDrone(String droneId) {
        droneService.deleteDrone(droneId);
        return ResponseEntity.noContent().build();
    }
}
