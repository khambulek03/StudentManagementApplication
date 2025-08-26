package com.codeio.studentmanagementapplication.repository;

import com.codeio.studentmanagementapplication.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    Optional<Module> findModuleByCode(String code);

    Optional<Module> findModuleByName(String name);

    boolean existsByModuleId(Long id);

    void removeModuleByModuleId(Long id);

    boolean existsByName(String name);

    Optional<Module> findModuleByModuleId(Long id);
}
