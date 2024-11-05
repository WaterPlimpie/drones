package com.demo.drones.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Setter;

@Setter
@Schema(name = "DroneCharge")
@Builder
public class DroneChargeDto {

    @Schema(description = "Drone ID")
    private String droneId;
    @Schema(description = "Battery percentage of the drone")
    private Double batteryPercentage;
}
