package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.CourseRequest;
import com.codeio.studentmanagementapplication.exception.DepartmentNotFoundException;
import com.codeio.studentmanagementapplication.model.Course;
import com.codeio.studentmanagementapplication.model.Department;
import com.codeio.studentmanagementapplication.repository.CourseRepository;
import com.codeio.studentmanagementapplication.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public Course addCourse(CourseRequest courseRequest) {
        Department department = this.departmentRepository.findDepartmentByDepartmentId(courseRequest.getDepartment_id())
                .orElseThrow(() -> new DepartmentNotFoundException(""));
        Course course = new Course();

        course.setCourseName(courseRequest.getCourseName());
        course.setCourseCode(courseRequest.getCourseCode());
        course.setDepartment(department);
        return this.courseRepository.save(course);
    }
}
