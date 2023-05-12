package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.CourseSubject;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseSubjectRepository extends CrudRepository<CourseSubject,Long> {

    Optional<CourseSubject> findCourseSubjectByCode(String code);
}
