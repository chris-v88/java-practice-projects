package org.emailapp.repositories;

import org.emailapp.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Spring generates: SELECT COUNT(*) > 0 WHERE name = ?
    boolean existsByName(String name);

    // Spring generates: SELECT COUNT(*) > 0 WHERE abbreviation = ?
    boolean existsByAbbreviation(String abbreviation);

    // Spring generates: SELECT * FROM departments WHERE name = ?
    // Optional means: returns the department if found, or empty if not found
    Optional<Department> findByName(String name);

    // Spring generates: SELECT * FROM departments WHERE abbreviation = ?
    Optional<Department> findByAbbreviation(String abbreviation);
}