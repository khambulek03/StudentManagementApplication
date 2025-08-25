package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.exception.CourseNotFoundException;
import com.codeio.studentmanagementapplication.exception.StudentNotFoundException;
import com.codeio.studentmanagementapplication.model.Course;
import com.codeio.studentmanagementapplication.model.Enrollment;
import com.codeio.studentmanagementapplication.model.Student;
import com.codeio.studentmanagementapplication.repository.CourseRepository;
import com.codeio.studentmanagementapplication.repository.EnrollmentRepository;
import com.codeio.studentmanagementapplication.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Enrollment enrollStudent(Long studentId, Long courseId) {
        Enrollment enrollment = new Enrollment();
        Student student = this.studentRepository.findStudentByStudentId(studentId)
                .orElseThrow(() -> new StudentNotFoundException(""));
        Course course = this.courseRepository.findCourseByCourseId(courseId)
                .orElseThrow(() -> new CourseNotFoundException(""));

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setDate(LocalDate.now());
        enrollment.setAchievedCredits(0);

        return this.enrollmentRepository.save(enrollment);
    }
}
