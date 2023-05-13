package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Course;
import com.learning.hadef.domain.entity.CourseSubject;
import com.learning.hadef.domain.value.CourseFeeCategory;
import com.learning.hadef.domain.value.CourseLevel;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface CourseRepository extends CrudRepository<Course, Long> , QuerydslPredicateExecutor<Course> {

    Iterable<Course> findByTitleARContainingOrTitleENContaining(String titleAr,String titleEN);

    Optional<Course> findBySlugTitleContaining(String slug);

    Iterable<Course> findByCourseLevel(CourseLevel level);

    Iterable<Course> findByCourseFeeCategory(CourseFeeCategory category);

    Optional<Course> findBySlugTitle(String slugTitle);
    Iterable<Course> findAllBySubjectsIn(Set<CourseSubject> subjects);
}