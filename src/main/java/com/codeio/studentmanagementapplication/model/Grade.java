package com.codeio.studentmanagementapplication.model;

import com.codeio.studentmanagementapplication.model.compositekeys.StudentModuleId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "grades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @EmbeddedId
    private StudentModuleId grade_id;

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("module_id")
    @JoinColumn(name = "module_id")
    private Module module;

    @NotNull
    private String assessment;

    @NotNull
    private double grade;

    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
