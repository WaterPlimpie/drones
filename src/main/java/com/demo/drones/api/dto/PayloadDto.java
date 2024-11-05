package com.demo.drones.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Schema(name = "Payload")
@Builder
public class PayloadDto {

    @Schema(description = "Payload of the drone")
    private List<MedicationDto> medications;

    @Schema(description = "Total weight of the payload")
    private Double totalWeight;
}
