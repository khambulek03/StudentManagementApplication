package com.codeio.studentmanagementapplication.repository;

import com.codeio.studentmanagementapplication.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
}
