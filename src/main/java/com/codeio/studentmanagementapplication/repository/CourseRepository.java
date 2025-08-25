package com.codeio.studentmanagementapplication.repository;

import com.codeio.studentmanagementapplication.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
