package com.codeio.studentmanagementapplication.repository;

import com.codeio.studentmanagementapplication.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findDepartmentByDepartmentId(Long departmentId);

    Optional<Department> findDepartmentByName(String name);

    void deleteDepartmentByDepartmentId(Long departmentId);
}
