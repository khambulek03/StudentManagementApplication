package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.DepartmentRequest;
import com.codeio.studentmanagementapplication.exception.DepartmentNotFoundException;
import com.codeio.studentmanagementapplication.exception.FacultyNotFoundException;
import com.codeio.studentmanagementapplication.model.Department;
import com.codeio.studentmanagementapplication.repository.DepartmentRepository;
import com.codeio.studentmanagementapplication.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Department addDepartment(DepartmentRequest departmentRequest) {
        Department department = createDepartment(departmentRequest);
        return this.departmentRepository.save(department);
    }

    private Department createDepartment(DepartmentRequest departmentRequest) {
        return new Department(
                departmentRequest.getName(),
                facultyRepository.findFacultyByFacultyId(departmentRequest.getFacultyId())
                        .orElseThrow(() -> new FacultyNotFoundException("Faculty does not exist"))
        );
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findDepartmentByDepartmentId(id)
                .orElseThrow(() -> new DepartmentNotFoundException("department with id " + id + " was not found."));
    }

    public void removeDepartment(Long departmentId) {
        Department department = departmentRepository.findDepartmentByDepartmentId(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(""));

        departmentRepository.deleteDepartmentByDepartmentId(departmentId);
    }
}
