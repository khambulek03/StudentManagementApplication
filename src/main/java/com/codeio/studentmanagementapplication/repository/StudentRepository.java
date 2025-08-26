package com.codeio.studentmanagementapplication.repository;

import com.codeio.studentmanagementapplication.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findStudentByStudentId(Long id);

    List<Student> findAllByLastName(String lastName);

    void removeStudentByStudentId(Long id);

    boolean existsByStudentNumber(Long studentNumber);

    void removeStudentByStudentNumber(Long studentNumber);
}
