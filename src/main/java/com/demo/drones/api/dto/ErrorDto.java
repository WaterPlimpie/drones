package com.demo.drones.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Schema(name = "Error")
public class ErrorDto {

    @Schema(description = "Error message", example = "Invalid request")
    private String message;

    @Schema(description = "Error code", example = "400")
    private Integer code;

    @Schema(description = "Error status", example = "BAD_REQUEST")
    private String status;

    @Schema(description = "Error timestamp", example = "2021-07-01T12:00:00Z")
    private Date timestamp;
}
