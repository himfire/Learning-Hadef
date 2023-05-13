package com.learning.hadef.service;

import com.learning.hadef.domain.dto.CourseDTO;
import com.learning.hadef.domain.dto.CourseSubjectDTO;
import com.learning.hadef.domain.dto.CoursesBySubjectDTO;
import com.learning.hadef.domain.dto.CreateCourseDTO;
import com.learning.hadef.domain.entity.Course;
import com.learning.hadef.domain.entity.CourseSubject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Set;

public interface CourseService {
    CourseDTO getCourseById(Long id);
    Course findCourseById(Long id);
    CourseDTO getCourseBySlugTitle(String slugTitle);
    Course findCourseBySlugTitle(String slugTitle);
    CreateCourseDTO createCourse(String lang, String chn, CreateCourseDTO dto);

    CourseDTO updateCourse(CourseDTO dto);

    void deleteCourse(Long id);

    List<CourseDTO> getAllCourses();
    CourseDTO updateCourseSubject(Set<CourseSubjectDTO> subjects, String slugTitle);
    CourseDTO addCoursesToCourseSubject(CoursesBySubjectDTO coursesBySubjects);
    List<Course> findAllCoursesBySubject(Set<String> subject);
    Set<CourseSubject> getCourseSubjects(Set<String> subjects);
}
