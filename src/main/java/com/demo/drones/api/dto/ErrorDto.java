package com.demo.drones.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(name = "Error")
public class ErrorDto {

    @Schema(description = "Error message", example = "Invalid request")
    private String message;

    @Schema(description = "Error code", example = "400")
    private Integer code;
}
