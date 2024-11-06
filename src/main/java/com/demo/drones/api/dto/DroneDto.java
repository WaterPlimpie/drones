package com.demo.drones.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(name = "Drone")
public class DroneDto {

    @Size(max = 100, message = "Serial number can be at most 100 characters")
    @NotBlank(message = "Serial number is mandatory")
    @Schema(description = "Serial number of the drone", requiredMode = Schema.RequiredMode.REQUIRED)
    private String serialNumber;

    @Builder.Default
    @Schema(description = "Model of the drone", requiredMode = Schema.RequiredMode.NOT_REQUIRED, defaultValue = "LIGHTWEIGHT", example = "LIGHTWEIGHT")
    private Model model = Model.LIGHTWEIGHT;

    @Max(value = 500, message = "Weight limit can be at most 500 grams")
    @Builder.Default
    @Schema(description = "Weight limit of the drone in grams")
    private Double weightLimit = 500.0;

    @Max(value = 200000, message = "Battery capacity can be at most 200000 volts")
    @Builder.Default
    @Schema(description = "Battery capacity of the drone in volts")
    private Integer batteryCapacity = 10000;

    @Builder.Default
    @Schema(description = "Current state of the drone", requiredMode = Schema.RequiredMode.NOT_REQUIRED, defaultValue = "IDLE", example = "IDLE")
    private State state = State.IDLE;

    public enum State {
        IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
    }

    public enum Model {
        LIGHTWEIGHT, MIDDLEWEIGHT, CRUISERWEIGHT, HEAVYWEIGHT
    }
}
