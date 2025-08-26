package com.codeio.studentmanagementapplication.service;

import com.codeio.studentmanagementapplication.dtos.ModuleRequest;
import com.codeio.studentmanagementapplication.exception.CourseNotFoundException;
import com.codeio.studentmanagementapplication.exception.DepartmentNotFoundException;
import com.codeio.studentmanagementapplication.exception.LecturerNotFoundException;
import com.codeio.studentmanagementapplication.exception.ModuleNotFoundException;
import com.codeio.studentmanagementapplication.model.Module;
import com.codeio.studentmanagementapplication.repository.CourseRepository;
import com.codeio.studentmanagementapplication.repository.DepartmentRepository;
import com.codeio.studentmanagementapplication.repository.LecturerRepository;
import com.codeio.studentmanagementapplication.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final LecturerRepository lecturerRepository;
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository,
                         LecturerRepository lecturerRepository,
                         CourseRepository courseRepository,
                         DepartmentRepository departmentRepository) {
        this.moduleRepository = moduleRepository;
        this.lecturerRepository = lecturerRepository;
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public Module addModule(ModuleRequest moduleRequest) {
        Module module = new Module();
        module.setName(moduleRequest.getName());
        module.setCode(moduleRequest.getCode());
        module.setCredits(moduleRequest.getCredits());
        module.setSemesterNumber(moduleRequest.getSemesterNumber());

        module.setDepartment(departmentRepository.findDepartmentByDepartmentId(
                moduleRequest.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(""))
        );

        module.setLecturer(lecturerRepository.findLecturerByLecturerId(
                moduleRequest.getLecturerId())
                .orElseThrow(() -> new LecturerNotFoundException(""))
        );

        module.setCourse(courseRepository.findCourseByCourseId(
                moduleRequest.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(""))
        );
        return this.moduleRepository.save(module);
    }

    public List<Module> getAllModules() {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("name"));
        return this.moduleRepository.findAll(pageable).getContent();
    }

    public Module getModuleByCode(String code) {
        return this.moduleRepository.findModuleByCode(code)
                .orElseThrow(() -> new ModuleNotFoundException(""));
    }

    public void removeCourse(Long id) {
        if (!moduleRepository.existsByModuleId(id)) {
            throw new ModuleNotFoundException("");
        }
        moduleRepository.removeModuleByModuleId(id);
    }

    public Module getModuleByName(String name) {
        return this.moduleRepository.findModuleByName(name)
                .orElseThrow(() -> new ModuleNotFoundException(""));
    }

    public Module updateModule(String moduleCode, ModuleRequest moduleRequest) {
        Module module = moduleRepository.findModuleByCode(moduleCode)
                .orElseThrow(() -> new ModuleNotFoundException(""));

        module.setName(moduleRequest.getName());
        module.setCode(moduleRequest.getCode());
        module.setLecturer(
                lecturerRepository.findLecturerByLecturerId(
                        moduleRequest.getLecturerId())
                        .orElseThrow(() -> new LecturerNotFoundException(""))
        );

        module.setCredits(moduleRequest.getCredits());

        return moduleRepository.save(module);
    }
}
