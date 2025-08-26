package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.GradeRequest;
import com.codeio.studentmanagementapplication.dtos.GradeUpdateRequest;
import com.codeio.studentmanagementapplication.exception.GradeNotFoundException;
import com.codeio.studentmanagementapplication.exception.ModuleNotFoundException;
import com.codeio.studentmanagementapplication.exception.StudentNotFoundException;
import com.codeio.studentmanagementapplication.model.Grade;
import com.codeio.studentmanagementapplication.model.Module;
import com.codeio.studentmanagementapplication.model.Student;
import com.codeio.studentmanagementapplication.repository.GradeRepository;
import com.codeio.studentmanagementapplication.repository.ModuleRepository;
import com.codeio.studentmanagementapplication.repository.StudentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    final GradeRepository gradeRepository;
    final StudentRepository studentRepository;
    final ModuleRepository moduleRepository;

    public GradeService(GradeRepository gradeRepository, StudentRepository studentRepository, ModuleRepository moduleRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.moduleRepository = moduleRepository;
    }


    Grade grade(GradeRequest gradeRequest) {
        Grade grade = new Grade();

        grade.setStudent(
                studentRepository.findStudentByStudentId(gradeRequest.getStudentId())
                        .orElseThrow(() -> new StudentNotFoundException(""))
        );

        grade.setModule(
                moduleRepository.findModuleByModuleId(gradeRequest.getModuleId())
                        .orElseThrow(() -> new ModuleNotFoundException(""))
        );

        grade.setAssessment(gradeRequest.getAssessment());
        grade.setGrade(grade.getGrade());
        return gradeRepository.save(grade);
    }

    Grade updateGrade(Long gradeId, GradeUpdateRequest gradeUpdateRequest) {
        Grade grade = this.gradeRepository.findGradeByGradeId(gradeId)
                .orElseThrow(() -> new GradeNotFoundException(""));

        grade.setAssessment(gradeUpdateRequest.getAssessment());
        grade.setGrade(gradeUpdateRequest.getGrade());

        return gradeRepository.save(grade);
    }

    Grade getGrade(Long id) {
        return gradeRepository.findGradeByGradeId(id)
                .orElseThrow(() -> new GradeNotFoundException(""));
    }

    List<Grade> getAllGrades() {
        Pageable pageable = PageRequest.of(0, 30, Sort.by("garde"));
        return gradeRepository.findAll(pageable).getContent();
    }

    List<Grade> getGradesByModuleId(Long moduleId) {
        Pageable pageable = PageRequest.of(0, 30, Sort.by("garde"));
        Module module = moduleRepository.findModuleByModuleId(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException(""));
        return gradeRepository.findAllByModule(pageable, module);
    }

    List<Grade> getGradesByStudentId(Long studentId) {
        Pageable pageable = PageRequest.of(0, 30, Sort.by("garde"));
        Student student = studentRepository.findStudentByStudentId(studentId)
                .orElseThrow(() -> new StudentNotFoundException(""));
        return gradeRepository.findAllByStudent(pageable, student);
    }

    List<Grade> getGradeByAssessment(String assessment) {
        Pageable pageable = PageRequest.of(0, 30, Sort.by("garde"));
        return gradeRepository.findAllByAssessment(pageable, assessment);
    }

}
