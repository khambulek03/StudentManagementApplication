package com.codeio.studentmanagementapplication.repository;

import com.codeio.studentmanagementapplication.model.Grade;
import com.codeio.studentmanagementapplication.model.Module;
import com.codeio.studentmanagementapplication.model.Student;
import com.codeio.studentmanagementapplication.model.compositekeys.StudentModuleId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, StudentModuleId> {
    Optional<Grade> findGradeByGradeId(Long gradeId);

    List<Grade> findAllByStudent(Pageable pageable, Student student);

    List<Grade> findAllByModule(Pageable pageable, Module module);

    List<Grade> findAllByAssessment(Pageable pageable, String assessment);
}
