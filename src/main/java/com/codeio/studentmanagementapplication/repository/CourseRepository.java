package com.codeio.studentmanagementapplication.repository;

import com.codeio.studentmanagementapplication.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseByCourseId(Long courseId);

    boolean existsByCourseId(Long id);

    void removeByCourseId(Long courseId);

    Optional<Course> findCourseByCourseCode(String courseCode);

    Optional<Course> findCourseByCourseName(String courseName);

    boolean existsByCourseCode(String courseCode);
}
