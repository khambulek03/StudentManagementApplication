package com.codeio.studentmanagementapplication.model.compositekeys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentModuleId implements Serializable {

    private Long student_id;
    private Long module_id;

    public StudentModuleId() {

    }

    public StudentModuleId(Long student_id, Long module_id) {
        this.student_id = student_id;
        this.module_id = module_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentModuleId)) return false;
        StudentModuleId that = (StudentModuleId) o;
        return Objects.equals(student_id, that.student_id) &&
                Objects.equals(module_id, that.module_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_id, module_id);
    }
}
