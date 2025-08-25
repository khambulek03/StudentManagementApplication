package com.codeio.studentmanagementapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lecturers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecturerId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @Column(unique = true)
    @NotBlank
    private String email;

    @Column(unique = true)
    @NotBlank
    private Long staffNumber;

    @Column(unique = true)
    @NotBlank
    private String phone;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private int age;

    @NotBlank
    private String gender;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Module> module = new ArrayList<>();

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
