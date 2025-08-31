package com.codeio.studentmanagementapplication.service;


import com.codeio.studentmanagementapplication.exception.FacultyNotFoundException;
import com.codeio.studentmanagementapplication.model.Faculty;
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
public class FacultyServiceTest {

    @InjectMocks private FacultyService facultyService;
    @Mock private FacultyRepository facultyRepository;

    @Test
    void shouldAddNewFaculty() {
        Faculty faculty = new Faculty();
        faculty.setFacultyId(1L);
        faculty.setFacultyName("Science");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        Faculty savedFaculty = facultyService.registerFaculty(faculty);

        assertNotNull(savedFaculty);
        assertEquals("Science", savedFaculty.getFacultyName());
        verify(facultyRepository).save(any(Faculty.class));
    }

    @Test
    void shouldCompletelyRemoveFacultyIfExist() {
        Long facultyId = 2L;

        Faculty faculty = new Faculty();
        faculty.setFacultyId(facultyId);
        faculty.setFacultyName("Commerce");

        when(facultyRepository.findFacultyByFacultyId(facultyId)).thenReturn(Optional.of(faculty));

        facultyService.removeFaculty(facultyId);

        verify(facultyRepository).removeFacultyByFacultyId(2L);
    }

    @Test
    void shouldThrowExceptionIfFacultyNotFound() {
        Faculty faculty = new Faculty();
        faculty.setFacultyId(1L);
        faculty.setFacultyName("Science");

        Long facultyId = 2L;

        when(facultyRepository.findFacultyByFacultyId(facultyId))
                .thenReturn(Optional.empty());

        assertThrows(FacultyNotFoundException.class,
                () -> facultyService.getFacultyById(facultyId));
    }
}
