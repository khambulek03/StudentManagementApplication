package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.StudentContactUpdateRequest;
import com.codeio.studentmanagementapplication.dtos.StudentRegisterRequest;
import com.codeio.studentmanagementapplication.exception.CourseNotFoundException;
import com.codeio.studentmanagementapplication.exception.DuplicationException;
import com.codeio.studentmanagementapplication.exception.StudentNotFoundException;
import com.codeio.studentmanagementapplication.model.Course;
import com.codeio.studentmanagementapplication.model.Student;
import com.codeio.studentmanagementapplication.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    private final CourseService courseService;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    public Student registerStudent(StudentRegisterRequest registerRequest) {
        logger.info("Registering a student {} for a course {}", registerRequest.getEmail(), registerRequest.getCourse_id());

        Course course;
        try {
            course = courseService.getCourseById(registerRequest.getCourse_id());
        } catch (CourseNotFoundException courseNotFoundException) {
            logger.error("Course with course id of {}", registerRequest.getCourse_id());
            throw courseNotFoundException;
        }

        Student student = registerHelper(registerRequest, course);
        logger.debug("Created a student object {}", student);

        Student registeredStudent = studentRepository.save(student);
        logger.info("Successfully registered student {} to the course {}",
                student.getEmail(), course.getCourseName());
        return registeredStudent;
    }

    private Student registerHelper(StudentRegisterRequest registerRequest, Course course) {
        logger.info("Creating a new unique student for a course {}", course.getCourseName());

        if (studentExistsByEmail(registerRequest.getEmail())) {
            logger.warn("Student with email {} already exists", registerRequest.getEmail());
            throw new DuplicationException("Duplication of students");
        }

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

        logger.debug("Successfully created student object: {}", student);
        return student;
    }

    private boolean studentExistsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    public List<Student> getAllStudents() {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("lastName"));
        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> getAllByLastName(String lastName) {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("lastName"));
        return studentRepository.findAllByLastName(pageable, lastName);
    }

    public Student getStudentByEmail(String email) {
        logger.info("Searching a student with {}", email);
        return studentRepository.findStudentByEmail(email)
                .orElseThrow(() -> {
                    logger.warn("No student was found with email {}", email);
                    return new StudentNotFoundException("Student does not exist");
                });
    }

    public Student getStudentById(Long id) {
        var student = studentRepository.findStudentByStudentId(id);
        return student.orElseThrow(() -> new StudentNotFoundException("student not found"));
    }

    public Student updateContactDetails(String email, StudentContactUpdateRequest contactUpdate) {
        Student student = studentRepository.findStudentByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("no student found with email: " + contactUpdate.getEmail()));

        student.setEmail(contactUpdate.getEmail());
        student.setPhone(contactUpdate.getPhone());
        return studentRepository.save(student);
    }

    public Student updateContactDetails(Long id, StudentContactUpdateRequest contactUpdate) {
        Student student = studentRepository.findStudentByStudentId(id)
                .orElseThrow(() -> new StudentNotFoundException("no student found with id: " + id));

        student.setEmail(contactUpdate.getEmail());
        student.setPhone(contactUpdate.getPhone());
        return studentRepository.save(student);
    }

    public void removeStudent(Long id) {
        if (!studentRepository.existsById(id))
            throw new StudentNotFoundException("student not found");
        studentRepository.removeStudentByStudentId(id);
    }

    public Student findStudentByStudentNumber(Long studentNumber) {
        logger.info("Searching student by a student number {}", studentNumber);
        return studentRepository.findStudentByStudentNumber(studentNumber)
                .orElseThrow(() -> {
                    logger.warn("Student with student number {} was not found", studentNumber);
                    throw new StudentNotFoundException("Student with student number " + studentNumber + " not found");
                });
    }

    public void removeStudentByStudentNumber(Long studentNumber) {
        logger.info("Removing a student with a student number {}", studentNumber);
        if(!studentRepository.existsByStudentNumber(studentNumber)){
            logger.warn("No student exists with student number {}", studentNumber);
            throw new StudentNotFoundException("");
        }

        Student student = findStudentByStudentNumber(studentNumber);
        studentRepository.removeStudentByStudentNumber(studentNumber);
        logger.info("Student was successfully remove {} {}", student.getFirstName(), student.getLastName());
    }
}
