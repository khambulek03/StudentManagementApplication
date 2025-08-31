package com.codeio.studentmanagementapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @Column(unique = true)
    @NotBlank
    private String email;

    @Column(unique = true)
    @NotNull
    private Long studentNumber;

    @Column(unique = true)
    @NotBlank
    private String phone;

    @NotNull
    private LocalDate dateOfBirth;

    @NotBlank
    private int age;

    @NotBlank
    private String gender;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

//    public Student(Long studentId, String firstName, String lastName, String email, Long studentNumber, String phone, LocalDate dateOfBirth, int age, String gender, Course course) {
//        this.studentId = studentId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.studentNumber = studentNumber;
//        this.phone = phone;
//        this.dateOfBirth = dateOfBirth;
//        this.age = age;
//        this.gender = gender;
//        this.course = course;
//    }
}


