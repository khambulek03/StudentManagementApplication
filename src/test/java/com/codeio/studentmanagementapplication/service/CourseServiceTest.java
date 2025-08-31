package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.CourseRequest;
import com.codeio.studentmanagementapplication.model.Course;
import com.codeio.studentmanagementapplication.model.Department;
import com.codeio.studentmanagementapplication.model.Faculty;
import com.codeio.studentmanagementapplication.repository.CourseRepository;
import com.codeio.studentmanagementapplication.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @InjectMocks private CourseService courseService;

    @Mock private CourseRepository courseRepository;
    @Mock private DepartmentRepository departmentRepository;

    @Test
    void shouldSuccessfullyAddNewCourse() {
        Department department = new Department();
        department.setDepartmentId(1L);
        department.setName("CS-IT");
        department.setFaculty(new Faculty(1L, "Science"));

        when(departmentRepository.findDepartmentByDepartmentId(1L))
                .thenReturn(Optional.of(department));

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseName("Bachelor of Science in Computer Science");
        courseRequest.setCourseCode("BSc CS");
        courseRequest.setTotalCredits(384);
        courseRequest.setDepartmentId(1L);

        Course course = new Course(1L, courseRequest.getCourseName(),
                courseRequest.getCourseCode(), courseRequest.getTotalCredits(), department);

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course savedCourse = courseService.addCourse(courseRequest);

        assertNotNull(savedCourse);
        assertEquals(courseRequest.getCourseName(), savedCourse.getCourseName());
        assertEquals(courseRequest.getCourseCode(), savedCourse.getCourseCode());
        assertEquals(department, savedCourse.getDepartment());

        verify(courseRepository).save(any(Course.class));
        verify(departmentRepository).findDepartmentByDepartmentId(1L);
    }

    @Test
    void shouldRemoveCourseByIdIfCourseExist() {
        Course course = new Course();
        course.setCourseId(1L);
        course.setCourseName("Computer Science");
        course.setCourseCode("CS");
        course.setTotalCredits(384);
        course.setDepartment(
                new Department("CS-IT",
                new Faculty(1L, "Science")));

        Long courseId = course.getCourseId();

        courseService.removeCourse(courseId);
        verify(courseRepository).removeByCourseId(courseId);
    }
}
