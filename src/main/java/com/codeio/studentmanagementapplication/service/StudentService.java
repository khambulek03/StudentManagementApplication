package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.StudentRegisterRequest;
import com.codeio.studentmanagementapplication.exception.StudentNotFoundException;
import com.codeio.studentmanagementapplication.model.Student;
import com.codeio.studentmanagementapplication.repository.StudentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student registerStudent(StudentRegisterRequest registerRequest) {
        var student = new Student();
        student.setFirstName(registerRequest.getFirstName());
        student.setLastName(registerRequest.getLastName());
        student.setEmail(registerRequest.getEmail());
        student.setPhone(registerRequest.getPhone());
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
        var student = this.studentRepository.findStudentByStudent_id(id);
        return student.orElseThrow(() -> new StudentNotFoundException("student not found"));
    }
}
