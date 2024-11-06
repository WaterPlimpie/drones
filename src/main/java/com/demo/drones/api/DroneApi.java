package com.demo.drones.api;

import com.demo.drones.api.dto.DroneChargeDto;
import com.demo.drones.api.dto.DroneDto;
import com.demo.drones.api.dto.ErrorDto;
import com.demo.drones.api.dto.PayloadDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.drones.api.ApiConstants.BASE_PATH;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(BASE_PATH + "/drones")
@Tag(name = "Drone API", description = "API to manage drones")
public interface DroneApi {

    @Operation(summary = "Register a drone", description = "Register a new drone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Drone registered successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error (required data missing)",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "General server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class)))
    })
    @ResponseStatus(CREATED)
    @PostMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> registerDrone(
            @RequestBody
            @Valid
            DroneDto droneDto);

    @Operation(summary = "Get drone battery percentage", description = "Get the battery percentage of a drone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone battery percentage retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Drone not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "General server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping(value = "/{droneId}/battery", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    ResponseEntity<DroneChargeDto> getBatteryPercentage(
            @PathVariable(name = "droneId")
            @Parameter(description = "Drone ID")
            String droneId);

    @Operation(summary = "Get drones", description = "Get a list of drones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drones retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "General server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    ResponseEntity<List<DroneDto>> getDrones(
            @RequestParam(name = "availableOnly", required = false, defaultValue = "false")
            @Parameter(description = "Filter drones by availability")
            boolean available,
            @RequestParam(name = "size", required = false, defaultValue = "10")
            @Parameter(description = "Number of drones to return")
            int size,
            @RequestParam(name = "from", required = false, defaultValue = "0")
            @Parameter(description = "Index of the first drone to return")
            int from);

    @Operation(summary = "Get drone", description = "Get a drone by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Drone not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "General server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping(value = "/{droneId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    ResponseEntity<DroneDto> getDrone(
            @PathVariable(name = "droneId")
            @Parameter(description = "Drone ID")
            String droneId);

    @Operation(summary = "Get drone payload", description = "Get the payload of a drone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone payload retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Drone not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "General server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping(value = "/{droneId}/payload", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    ResponseEntity<PayloadDto> getPayload(
            @PathVariable(name = "droneId")
            @Parameter(description = "Drone ID")
            String droneId);

    @Operation(summary = "Load a drone", description = "Load a drone with a payload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone loaded successfully"),
            @ApiResponse(responseCode = "404", description = "Drone not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "Medication not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "General server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class)))
    })
    @PostMapping(value = "/{droneId}/load", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    ResponseEntity<Void> loadDrone(
            @PathVariable(name = "droneId")
            @Parameter(description = "Drone ID")
            String droneId,
            @RequestBody
            @Valid
            PayloadDto payloadDto);

    @Operation(summary = "Delete a drone", description = "Delete a drone by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Drone not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "General server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class)))
    })
    @DeleteMapping(value = "/{droneId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    ResponseEntity<Void> deleteDrone(
            @PathVariable(name = "droneId")
            @Parameter(description = "Drone ID")
            String droneId);

}
