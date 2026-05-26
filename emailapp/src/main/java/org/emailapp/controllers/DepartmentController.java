package org.emailapp.controllers;

import java.util.List;

import org.emailapp.dto.CreateDepartmentRequest;
import org.emailapp.dto.DepartmentResponse;
import org.emailapp.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

// @RestController means: this class handles HTTP requests and returns JSON automatically
// @RequestMapping("/api/departments") means every endpoint in here starts with /api/departments
// @CrossOrigin allows your React app (running on port 5173) to call this backend (port 8080)
@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "http://localhost:5173")
public class DepartmentController {
    private final DepartmentService departmentService;

    // Constructor injection of the service
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // GET /api/departments - get all departments
    // Return a list of all departments in the database as JSON
    // @GetMapping("/all") means this method handles GET requests to /api/departments/all
    @GetMapping("/all")
    public List<DepartmentResponse> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // POST
    // Body: { "name": "Sales", "abbreviation": "sale" }
    // @Valid trigger @NotBlank validation rules on CreateDepartmentRequest
    // ResponseEntity lets you control the HTTP Status Code return
    @PostMapping("/create")
    public ResponseEntity<DepartmentResponse> createDepartment(
        @Valid @RequestBody CreateDepartmentRequest request
    ) {
        DepartmentResponse response = departmentService.createDepartment(request);

        // 201: Created is the correct HTTP status when a new resource is created
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}