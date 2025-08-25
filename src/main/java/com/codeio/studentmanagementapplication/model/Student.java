package com.codeio.studentmanagementapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;

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
    private Date dateOfBirth;

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
}
