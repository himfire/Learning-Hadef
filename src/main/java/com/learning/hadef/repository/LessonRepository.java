package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson,Long> {
}
