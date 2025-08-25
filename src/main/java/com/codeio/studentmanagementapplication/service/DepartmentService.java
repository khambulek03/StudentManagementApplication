package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.DepartmentRequest;
import com.codeio.studentmanagementapplication.exception.FacultyNotFoundException;
import com.codeio.studentmanagementapplication.model.Department;
import com.codeio.studentmanagementapplication.model.Faculty;
import com.codeio.studentmanagementapplication.repository.DepartmentRepository;
import com.codeio.studentmanagementapplication.repository.FacultyRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;

    public DepartmentService(DepartmentRepository departmentRepository, FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Department addDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setName(departmentRequest.getName());

        Faculty faculty = this.facultyRepository.findFacultyByFacultyId(departmentRequest.getFacultyId())
                .orElseThrow(() -> new FacultyNotFoundException(""));
        department.setFaculty(faculty);
        return this.departmentRepository.save(department);
    }
}
