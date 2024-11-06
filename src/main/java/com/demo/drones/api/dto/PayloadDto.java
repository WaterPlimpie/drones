package com.demo.drones.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(name = "Payload")
@Builder
public class PayloadDto {

    @Schema(description = "Name of the medication")
    private String medicationName;

    @Schema(description = "Id of the medication")
    private String medicationId;
}
