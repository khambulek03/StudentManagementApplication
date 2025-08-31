package com.codeio.studentmanagementapplication.controller;

import com.codeio.studentmanagementapplication.dtos.StudentContactUpdateRequest;
import com.codeio.studentmanagementapplication.dtos.StudentRegisterRequest;
import com.codeio.studentmanagementapplication.model.Student;
import com.codeio.studentmanagementapplication.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1.0/student-management-system/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/student-lastname/{lastname}")
    public ResponseEntity<List<Student>> getStudentByLastName(@PathVariable String lastname) {
        return ResponseEntity.ok(studentService.getAllByLastName(lastname));
    }

    @GetMapping("/student-email/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        Student student = studentService.getStudentByEmail(email);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping("/student-registration")
    public ResponseEntity<Student> registerStudent(@Valid @RequestBody StudentRegisterRequest registerRequest) {
        Student student = studentService.registerStudent(registerRequest);
        return student != null
                ? ResponseEntity.status(HttpStatus.OK).body(student)
                : ResponseEntity.badRequest().build();
    }

    @PostMapping("/update-contact/{id}")
    public ResponseEntity<?> updateContacts(@Valid @RequestBody StudentContactUpdateRequest contactUpdateRequest,
                                                  @PathVariable Long id) {
        Student student = studentService.updateContactDetails(id, contactUpdateRequest);
        return student != null
                ? ResponseEntity.status(HttpStatus.OK).body(student)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student was not found");
    }

    @DeleteMapping("/remove-student/{id}")
    public ResponseEntity<?> removeStudent(@PathVariable Long id) {
        studentService.removeStudent(id);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
}
