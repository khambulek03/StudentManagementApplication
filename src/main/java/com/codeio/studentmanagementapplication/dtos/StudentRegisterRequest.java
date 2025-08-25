package com.codeio.studentmanagementapplication.dtos;

import com.codeio.studentmanagementapplication.model.Course;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class StudentRegisterRequest {

    private String firstName;

    private String lastName;

    private String email;

    private Long studentNumber;

    private String phone;

    private Date dateOfBirth;

    private int age;

    private String gender;

    private Long course_id;
}
