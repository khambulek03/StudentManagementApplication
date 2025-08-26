package com.codeio.studentmanagementapplication.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModuleRequest {
    private String name;

    private String code;

    private int semesterNumber;

    private int credits;

    private Long courseId;

    private Long lecturerId;

    private Long departmentId;
}
