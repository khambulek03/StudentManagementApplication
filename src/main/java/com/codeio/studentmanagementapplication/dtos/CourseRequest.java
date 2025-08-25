package com.codeio.studentmanagementapplication.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseRequest {

    @NotBlank
    public String courseName;

    @Column(unique = true)
    public String courseCode;

    @NotNull
    public int totalCredits;

    @ManyToOne
    @JoinColumn(name = "department_id")
    public Long department_id;
}
