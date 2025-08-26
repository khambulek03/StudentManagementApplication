package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.LecturerRegisterRequest;
import com.codeio.studentmanagementapplication.exception.ModuleNotFoundException;
import com.codeio.studentmanagementapplication.model.Lecturer;
import com.codeio.studentmanagementapplication.repository.LecturerRepository;
import com.codeio.studentmanagementapplication.repository.ModuleRepository;
import org.springframework.stereotype.Service;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;
    private final ModuleRepository moduleRepository;

    public LecturerService(LecturerRepository lecturerRepository, ModuleRepository moduleRepository) {
        this.lecturerRepository = lecturerRepository;
        this.moduleRepository = moduleRepository;
    }

    public Lecturer addLecturer(LecturerRegisterRequest registerRequest) {
        Lecturer lecturer = new Lecturer();

        lecturer.setFirstName(registerRequest.getFirstName());
        lecturer.setLastName(registerRequest.getLastName());
        lecturer.setEmail(registerRequest.getEmail());
        lecturer.setPhone(registerRequest.getPhone());
        lecturer.setGender(registerRequest.getGender());
        lecturer.setAge(registerRequest.getAge());
        lecturer.setDateOfBirth(registerRequest.getDateOfBirth());
        lecturer.setStaffNumber(registerRequest.getStudentNumber());

        return this.lecturerRepository.save(lecturer);
    }
}