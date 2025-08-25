package com.codeio.studentmanagementapplication.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseUpdateRequest {

    private String courseName;

    private String courseCode;

    private String departmentId;

    private int totalCredits;
}
