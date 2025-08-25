package com.codeio.studentmanagementapplication.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


public class CourseRequest {

    @NotBlank
    private String courseName;

    @Column(unique = true)
    private String courseCode;

    @NotNull
    private int totalCredits;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Long department_id;
}
