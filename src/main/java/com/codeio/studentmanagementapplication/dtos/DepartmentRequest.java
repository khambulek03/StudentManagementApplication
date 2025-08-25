package com.codeio.studentmanagementapplication.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
    @NotBlank
    private String name;

    private Long facultyId;
}
