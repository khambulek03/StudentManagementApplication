package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.DepartmentRequest;
import com.codeio.studentmanagementapplication.exception.DepartmentNotFoundException;
import com.codeio.studentmanagementapplication.exception.FacultyNotFoundException;
import com.codeio.studentmanagementapplication.model.Department;
import com.codeio.studentmanagementapplication.model.Faculty;
import com.codeio.studentmanagementapplication.repository.DepartmentRepository;
import com.codeio.studentmanagementapplication.repository.FacultyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private FacultyRepository facultyRepository;
    
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void shouldAddNewDepartmentSuccessfully() {
        DepartmentRequest request = new DepartmentRequest("Computer Science", 1L);
        Faculty faculty = new Faculty();
        faculty.setFacultyId(1L);
        faculty.setFacultyName("Science");

        when(facultyRepository.findFacultyByFacultyId(1L)).thenReturn(Optional.of(faculty));

        Department department = new Department(request.getName(), faculty);

        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Department savedDepartment = departmentService.addDepartment(request);

        assertNotNull(savedDepartment);
        assertEquals("Computer Science", savedDepartment.getName());
        assertEquals("Science", savedDepartment.getFaculty().getFacultyName());

        verify(departmentRepository).save(any(Department.class));
        verify(facultyRepository).findFacultyByFacultyId(1L);
    }

    @Test
    void shouldThrowExceptionWhenFacultyNotFound() {
        DepartmentRequest request = new DepartmentRequest("Maths", 999L);

        when(facultyRepository.findFacultyByFacultyId(999L))
                .thenReturn(Optional.empty());

        assertThrows(FacultyNotFoundException.class,
                () -> departmentService.addDepartment(request));
    }

    @Test
    void shouldRemoveDepartmentByDepartmentIdWhenExist() {
        DepartmentRequest request = new DepartmentRequest("CS-IT", 1L);
        Faculty faculty = new Faculty(1L, "Science");
        when(facultyRepository.findFacultyByFacultyId(1L)).thenReturn(Optional.of(faculty));

        Department department = new Department();
        department.setDepartmentId(2L);
        department.setName(request.getName());
        department.setFaculty(faculty);
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Long departmentId = department.getDepartmentId();
        Department savedDepartment = departmentService.addDepartment(request);
        assertNotNull(savedDepartment);
        assertEquals("CS-IT", savedDepartment.getName());
        verify(departmentRepository).save(any(Department.class));

        when(departmentRepository.findDepartmentByDepartmentId(departmentId))
                .thenReturn(Optional.of(department));

        assertEquals(departmentId, savedDepartment.getDepartmentId());
        departmentService.removeDepartment(departmentId);

        verify(departmentRepository).deleteDepartmentByDepartmentId(departmentId);
    }

    @Test
    void shouldThrowDepartmentNotFoundException() {
        Long departmentId = 10L;

        when(departmentRepository.findDepartmentByDepartmentId(departmentId))
                .thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class,
                () -> departmentService.getDepartmentById(departmentId));
    }
}
