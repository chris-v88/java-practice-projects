package org.emailapp.dto;

public record DepartmentResponse(
    Long id,
    String name,
    String abbreviation,
    int numberOfEmployees
) {}