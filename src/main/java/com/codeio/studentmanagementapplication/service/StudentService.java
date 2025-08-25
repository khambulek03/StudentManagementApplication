package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.StudentContactUpdateRequest;
import com.codeio.studentmanagementapplication.dtos.StudentRegisterRequest;
import com.codeio.studentmanagementapplication.exception.CourseNotFoundException;
import com.codeio.studentmanagementapplication.exception.StudentNotFoundException;
import com.codeio.studentmanagementapplication.model.Course;
import com.codeio.studentmanagementapplication.model.Student;
import com.codeio.studentmanagementapplication.repository.CourseRepository;
import com.codeio.studentmanagementapplication.repository.StudentRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Student registerStudent(StudentRegisterRequest registerRequest) {
        Course course = this.courseRepository.findCourseByCourseId(registerRequest.getCourse_id())
                .orElseThrow(() -> new CourseNotFoundException("course does not exist"));

        Student student = new Student();
        student.setFirstName(registerRequest.getFirstName());
        student.setLastName(registerRequest.getLastName());
        student.setEmail(registerRequest.getEmail());
        student.setPhone(registerRequest.getPhone());
        student.setDateOfBirth(registerRequest.getDateOfBirth());
        student.setStudentNumber(registerRequest.getStudentNumber());
        student.setAge(registerRequest.getAge());
        student.setGender(registerRequest.getGender());
        student.setCourse(course);
        return this.studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("lastName"));
        return this.studentRepository.findAll(pageable).getContent();
    }

    public List<Student> getAllByLastName(String lastName) {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("lastName"));
        return this.studentRepository.findAllByLastName(lastName);
    }

    public Student getStudentByEmail(String email) {
        var student = this.studentRepository.findStudentByEmail(email);
        return student.orElseThrow(() -> new StudentNotFoundException("Student does not exist"));
    }

    public Student getStudentById(Long id) {
        var student = this.studentRepository.findStudentByStudentId(id);
        return student.orElseThrow(() -> new StudentNotFoundException("student not found"));
    }

    public Student updateContactDetails(String email, StudentContactUpdateRequest contactUpdate) {
        var student = this.studentRepository.findStudentByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("no student found with email: " + contactUpdate.getEmail()));

        student.setEmail(contactUpdate.getEmail());
        student.setPhone(contactUpdate.getPhone());
        return this.studentRepository.save(student);
    }

    public Student updateContactDetails(Long id, StudentContactUpdateRequest contactUpdate) {
        var student = this.studentRepository.findStudentByStudentId(id)
                .orElseThrow(() -> new StudentNotFoundException("no student found with id: " + id));

        student.setEmail(contactUpdate.getEmail());
        student.setPhone(contactUpdate.getPhone());
        return this.studentRepository.save(student);
    }

    public void removeStudent(Long id) {
        if (!this.studentRepository.existsById(id))
            throw new StudentNotFoundException("student not found");
        this.studentRepository.removeStudentByStudentId(id);
    }
}
