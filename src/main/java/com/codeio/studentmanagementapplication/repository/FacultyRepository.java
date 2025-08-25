package com.codeio.studentmanagementapplication.repository;

import com.codeio.studentmanagementapplication.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Optional<Faculty> findFacultyByFacultyId(Long facultyId);
}
