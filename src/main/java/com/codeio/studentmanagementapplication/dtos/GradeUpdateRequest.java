package com.codeio.studentmanagementapplication.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GradeUpdateRequest {
    private String assessment;

    private double grade;
}
