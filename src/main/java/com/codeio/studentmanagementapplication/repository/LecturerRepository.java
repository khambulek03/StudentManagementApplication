package com.codeio.studentmanagementapplication.repository;

import com.codeio.studentmanagementapplication.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    Optional<Lecturer> findLecturerByLecturerId(Long id);
}
