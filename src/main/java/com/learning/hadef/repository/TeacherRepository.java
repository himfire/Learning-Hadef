package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher,Long> {
}
