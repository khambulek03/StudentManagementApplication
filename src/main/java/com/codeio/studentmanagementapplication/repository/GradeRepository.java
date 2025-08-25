package com.codeio.studentmanagementapplication.repository;

import com.codeio.studentmanagementapplication.model.Grade;
import com.codeio.studentmanagementapplication.model.compositekeys.StudentModuleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, StudentModuleId> {
}
