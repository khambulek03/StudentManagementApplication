package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.CourseRequest;
import com.codeio.studentmanagementapplication.dtos.CourseUpdateRequest;
import com.codeio.studentmanagementapplication.exception.CourseNotFoundException;
import com.codeio.studentmanagementapplication.exception.DepartmentNotFoundException;
import com.codeio.studentmanagementapplication.model.Course;
import com.codeio.studentmanagementapplication.model.Department;
import com.codeio.studentmanagementapplication.repository.CourseRepository;
import com.codeio.studentmanagementapplication.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public Course addCourse(CourseRequest courseRequest) {
        Department department = this.departmentRepository.findDepartmentByDepartmentId(courseRequest.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(""));
        Course course = new Course();

        course.setCourseName(courseRequest.getCourseName());
        course.setCourseCode(courseRequest.getCourseCode());
        course.setDepartment(department);
        return this.courseRepository.save(course);
    }

    public void removeCourse(Long courseId) {
        if (this.courseRepository.existsByCourseId(courseId))
            throw new CourseNotFoundException("Course not found");

        this.courseRepository.removeByCourseId(courseId);
    }

    public List<Course> getAllCourses(){
        Pageable pageable = PageRequest.of(0, 20, Sort.by("courseName"));
        return this.courseRepository.findAll(pageable).getContent();
    }

    public Course getCourseByName(String courseName) {
        return this.courseRepository.findCourseByCourseName(courseName)
                .orElseThrow(() -> new CourseNotFoundException(""));
    }

    public Course getCourseByCode(String courseCode) {
        return this.courseRepository.findCourseByCourseCode(courseCode)
                .orElseThrow(() -> new CourseNotFoundException(""));
    }

    public Course getCourseById(Long id) {
        logger.info("Fetching a course with an id of {}", id);
        return courseRepository.findCourseByCourseId(id)
                .orElseThrow(() -> {
                    logger.warn("Course with id {} was not found", id);
                    return new CourseNotFoundException("");
                });
    }

    public Course updateCourse(String courseCode, CourseUpdateRequest updateRequest) {
        if (!this.courseRepository.existsByCourseCode(courseCode))
            throw new CourseNotFoundException("");

        Course course = this.getCourseByCode(courseCode);
        course.setCourseName(updateRequest.getCourseName());
        course.setCourseCode(updateRequest.getCourseCode());
        course.setTotalCredits(updateRequest.getTotalCredits());

        return this.courseRepository.save(course);
    }
}
