package com.demo.drones.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Schema(name = "Medication")
@Builder
public class MedicationDto {

    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Name can contain only letters, numbers, ‘-‘, ‘_’")
    @Size(min = 1, max = 100, message = "Name can be at most 100 characters")
    @NotBlank(message = "Name is mandatory")
    @Schema(description = "Name of the medication", requiredMode = Schema.RequiredMode.REQUIRED, example = "Paracetamol")
    private String name;

    @Max(value = 500, message = "Weight can be at most 500 grams")
    @Min(value = 1, message = "Weight must be at least 1 gram")
    @NotNull(message = "Weight is mandatory")
    @Schema(description = "Weight of the medication in grams", requiredMode = Schema.RequiredMode.REQUIRED, example = "250")
    private Double weight;

    @Pattern(regexp = "^[A-Z0-9_]*$", message = "Code can contain only upper case letters, underscore and numbers")
    @Size(min = 1, max = 20, message = "Code can be at most 20 characters")
    @NotBlank(message = "Code is mandatory")
    @Schema(description = "Code of the medication", requiredMode = Schema.RequiredMode.REQUIRED, example = "PARACETAMOL")
    private String code;

    @Schema(description = "Image of the medication")
    private MultipartFile image;
}
