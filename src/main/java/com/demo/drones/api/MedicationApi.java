package com.demo.drones.api;

import com.demo.drones.api.dto.MedicationDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.drones.api.ApiConstants.BASE_PATH;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RequestMapping(BASE_PATH + "/medications")
public interface MedicationApi {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medication registered successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error (required data missing)"),
            @ApiResponse(responseCode = "500", description = "General server error")
    })
    @PostMapping(value = "/", consumes = MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(CREATED)
    ResponseEntity<Void> registerMedication(@RequestBody
                                            @Valid
                                            MedicationDto medicationDto);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medications retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "General server error")
    })
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    ResponseEntity<List<MedicationDto>> getMedications(
            @RequestParam(name = "size", required = false, defaultValue = "10")
            @Parameter(description = "Number of medications to return")
            int size,
            @RequestParam(name = "from", required = false, defaultValue = "0")
            @Parameter(description = "Starting index of medications to return")
            int from);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medication retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Medication not found"),
            @ApiResponse(responseCode = "500", description = "General server error")
    })
    @GetMapping(value = "/{medicationId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    ResponseEntity<MedicationDto> getMedication(
            @PathVariable(name = "medicationId")
            @Parameter(description = "Medication ID")
            String medicationId);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Medication updated successfully"),
            @ApiResponse(responseCode = "404", description = "Medication not found"),
            @ApiResponse(responseCode = "500", description = "General server error")
    })
    @PutMapping(value = "/{medicationId}", consumes = MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(NO_CONTENT)
    ResponseEntity<Void> updateMedication(
            @PathVariable(name = "medicationId")
            @Parameter(description = "Medication ID")
            String medicationId,
            @RequestBody
            @Valid
            MedicationDto medicationDto);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Medication deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Medication not found"),
            @ApiResponse(responseCode = "500", description = "General server error")
    })
    @DeleteMapping(value = "/{medicationId}")
    @ResponseStatus(NO_CONTENT)
    ResponseEntity<Void> deleteMedication(
            @PathVariable(name = "medicationId")
            @Parameter(description = "Medication ID")
            String medicationId);

}
