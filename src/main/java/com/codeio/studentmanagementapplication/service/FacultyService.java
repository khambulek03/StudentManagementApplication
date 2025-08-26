package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.exception.FacultyNotFoundException;
import com.codeio.studentmanagementapplication.model.Faculty;
import com.codeio.studentmanagementapplication.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Faculty registerFaculty(Faculty faculty) {
        return this.facultyRepository.save(faculty);
    }

    void removeFaculty(Long facultyId) {
        Faculty faculty = facultyRepository.findFacultyByFacultyId(facultyId)
                .orElseThrow(() -> new FacultyNotFoundException(""));
        facultyRepository.removeFacultyByFacultyId(faculty.getFacultyId());
    }

    List<Faculty> getAllFaculties() {
        Pageable pageable = PageRequest.of(0, 25, Sort.by("facultyName"));
        return facultyRepository.findAll(pageable).getContent();
    }

    Faculty getFacultyByName(String name) {
        return facultyRepository.findFacultyByFacultyName(name)
                .orElseThrow(() -> new FacultyNotFoundException(""));
    }
}
