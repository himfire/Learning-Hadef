package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {
}
