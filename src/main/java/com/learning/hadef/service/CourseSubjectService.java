package com.learning.hadef.service;

import com.learning.hadef.domain.dto.CourseDTO;
import com.learning.hadef.domain.dto.CourseSubjectDTO;
import com.learning.hadef.domain.entity.CourseSubject;

import java.util.List;

public interface CourseSubjectService {

    List<CourseSubjectDTO> getAllCourseSubjects();
    void deleteCourseSubjectById(Long courseSubjectId);
    void deleteCourseSubject(CourseSubjectDTO courseSubject);
    CourseSubjectDTO createCourseSubject(CourseSubjectDTO courseSubject);
    CourseSubjectDTO updateCourseSubject(CourseSubjectDTO courseSubjectDto);
    CourseSubjectDTO getCourseSubjectById(Long id);
    CourseSubject findCourseSubjectById(Long id);
    CourseSubjectDTO getCourseSubjectByCode(String code);
    CourseSubject findCourseSubjectByCode(String code);
}
