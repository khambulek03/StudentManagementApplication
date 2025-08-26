package com.codeio.studentmanagementapplication.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GradeRequest {

    private Long studentId;

    private Long moduleId;

    private String assessment;

    private double grade;
}
