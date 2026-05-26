package org.emailapp.dto;

// This is what the server sends back to the browser.
// Notice: no password here. We never send passwords in responses.
public record AccountResponse(
    Long id,
    String firstName,
    String lastName,
    String email,
    DepartmentResponse department,
    int mailboxCapacity,
    String alternativeEmail
) {}
// The AccountResponse record is a simple data transfer object (DTO) that represents the information about an email account that we want to send back to the client.