package org.emailapp.dto;

import jakarta.validation.constraints.NotBlank;

// This is a Java "record" — a modern shorthand for a simple data class.
// It automatically creates the constructor, getters, equals, hashCode, toString.
// @NotBlank means the field is required AND cannot be empty spaces.
public record CreateAccountRequest(
    @NotBlank(message = "First name is required") String firstName,
    @NotBlank(message = "Last name is required") String lastName,
    @NotBlank(message = "Department abbreviation is required") String departmentAbbreviation
) {}