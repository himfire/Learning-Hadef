package com.learning.hadef.service;

import com.learning.hadef.domain.dto.CourseDTO;
import com.learning.hadef.domain.dto.CoursesBySubjectDTO;
import com.learning.hadef.domain.dto.CreateCourseDTO;
import com.learning.hadef.domain.entity.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Set;

public interface CourseService {
    CourseDTO getCourseById(Long id);
    CourseDTO getCourseBySlugTitle(String id);

    CreateCourseDTO createCourse(String lang, String chn, CreateCourseDTO dto);

    CourseDTO updateCourse(CourseDTO dto);

    void deleteCourse(Long id);

    List<CourseDTO> getAllCourses();
    List<CourseDTO> getAllCoursesBySubject(String subject);
    CourseDTO updateCourseSubject(Set<String> subjects, String slugTitle);
    List<CoursesBySubjectDTO> getAllCoursesBySubject(Set<String> subject);
}
