package org.emailapp.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateDepartmentRequest(
    @NotBlank(message = "Department name is required") String name,
    @NotBlank(message = "Department abbreviation is required") String abbreviation
) {}