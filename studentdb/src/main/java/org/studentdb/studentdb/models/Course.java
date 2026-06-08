package org.studentdb.studentdb.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String courseCode;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private BigDecimal cost;

    public Course() {}

    // Constructor to receive the first name and last name
    public Course(String courseCode, String courseName, BigDecimal cost) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.cost = cost;
    }

    // --- GETTERS ---
    public Long getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    // --- SETTERS ---
}
