package com.codeio.studentmanagementapplication.dtos;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class StudentRegisterRequest {

    private String firstName;

    private String lastName;

    private String email;

    private Long studentNumber;

    private String phone;

    private LocalDate dateOfBirth;

    private int age;

    private String gender;

    private Long course_id;
}
