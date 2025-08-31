package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.StudentRegisterRequest;
import com.codeio.studentmanagementapplication.model.Course;
import com.codeio.studentmanagementapplication.model.Student;
import com.codeio.studentmanagementapplication.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks private StudentService studentService;
    @InjectMocks private CourseService courseService;
    @Mock private StudentRepository studentRepository;

    @Test
    void shouldAddNewStudentWithCourse() {
        Course course = courseService.getCourseByCode("C-1");
        StudentRegisterRequest student = new StudentRegisterRequest();
        student.setFirstName("Mhlengi");
        student.setLastName("Mncube");
        student.setEmail("khambulek03@gmail.com");
        student.setGender("Male");
        student.setDateOfBirth(LocalDate.of(2003, 05, 29));
        student.setPhone("0799610140");
        student.setStudentNumber(222090853L);
        student.setAge(22);
        student.setCourse_id(course.getCourseId());

        Student registerStudent = new Student();
//        when(studentRepository.save(any(Student.class))).thenReturn(student);
//
//        Student savedStudent = studentService.registerStudent(student);
    }
}
