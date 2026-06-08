package org.studentdb.studentdb.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer gradeLevel;

    @Column(nullable = false)
    private BigDecimal balance;

    // we don't use @JoinColumn on here
    // because the foreign key is not stored in the Student table
    @OneToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();

    public Student() {}

    // Constructor to receive the first name and last name
    public Student(String firstName, String lastName, Integer gradeLevel, BigDecimal balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gradeLevel = gradeLevel;
        this.balance = balance;
    }

    // --- GETTERS ---
    public Long getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Enrollment> getEnrollments() {
        return this.enrollments;
    }

    // --- SETTERS ---
}
