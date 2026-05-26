package org.emailapp.services;

import org.emailapp.dto.CreateDepartmentRequest;
import org.emailapp.dto.DepartmentResponse;
import org.emailapp.models.Department;
import org.emailapp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentResponse createDepartment(CreateDepartmentRequest request) {
        if (departmentRepository.existsByName(request.name())) {
            throw new IllegalArgumentException("Department name already exists: " + request.name());
        }

        Department department = new Department(request.name(), request.abbreviation());
        // .save() will insert the new department into the database and return the saved entity (with generated ID)
        Department saved = departmentRepository.save(department);

        // toResponse() is a helper method to convert Department entity to DepartmentResponse DTO
        return toResponse(saved);
    }

    public List<DepartmentResponse> getAllDepartments() {
        // .findAll() returns a list of all Department entities from the database
        List<Department> departments = departmentRepository.findAll();

        // Convert each Department entity to DepartmentResponse DTO using the toResponse() helper method
        return departments.stream() // Convert the list to a stream, which allows us to perform functional operations on it
                .map(this::toResponse) // For each Department entity in the stream, call the toResponse() method to convert it to a DepartmentResponse DTO
                .collect(Collectors.toList()); // Collect the results of the stream operations back into a List of DepartmentResponse DTOs and return it
    }

    // Converts a Department entity to a DepartmentResponse DTO
    // Notice: numberOfEmployees comes from the Department entity's getNumberOfEmployees() method
    // which counts how many accounts are in that department --> numberOfEmployees comes from accounts.size()
    private DepartmentResponse toResponse(Department department) {
        // Response follow structure defined in DepartmentResponse record: id, name, abbreviation, numberOfEmployees
        return new DepartmentResponse(
            department.getId(),
            department.getName(),
            department.getAbbreviation(),
            department.getNumberOfEmployees()
        );
    }


}
