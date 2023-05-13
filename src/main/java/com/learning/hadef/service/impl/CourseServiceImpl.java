package com.learning.hadef.service.impl;

import ch.qos.logback.core.pattern.ConverterUtil;
import com.google.common.collect.ImmutableList;
import com.learning.hadef.domain.dto.CourseDTO;
import com.learning.hadef.domain.dto.CourseSubjectDTO;
import com.learning.hadef.domain.dto.CoursesBySubjectDTO;
import com.learning.hadef.domain.dto.CreateCourseDTO;
import com.learning.hadef.domain.entity.Course;
import com.learning.hadef.domain.entity.CourseSubject;
import com.learning.hadef.exception.CustomException;
import com.learning.hadef.repository.CourseRepository;
import com.learning.hadef.service.CourseService;
import com.learning.hadef.service.CourseSubjectService;
import com.learning.hadef.util.AbstractConverterUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.learning.hadef.util.LoggingUtil.logError;
import static com.learning.hadef.util.LoggingUtil.logInfo;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final String serviceName;
    private final ModelMapper modelMapper;
    private final CourseSubjectService courseSubjectService;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper, CourseSubjectService courseSubjectService) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
        this.courseSubjectService = courseSubjectService;
        this.serviceName = this.getClass().getName();
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        logInfo(serviceName,"Started searching for course with id "+id);
        Course course = findCourseById(id);
        if(course != null){
            return modelMapper.map(course,CourseDTO.class);
        }
        return null;
    }

    @Override
    public Course findCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            logInfo(serviceName,"Found course with slug title %d",id);
            return course.get();
        }
        logInfo(serviceName,"Error in fetching course with id %d",id);
        return null;
    }

    @Override
    public CourseDTO getCourseBySlugTitle(String slugTitle) {
        logInfo(serviceName,"Started searching for course with slug title "+slugTitle);
        Course course = findCourseBySlugTitle(slugTitle);
        if(course != null){
            return modelMapper.map(course,CourseDTO.class);
        }
        return null;
    }

    @Override
    public Course findCourseBySlugTitle(String slugTitle) {
        Optional<Course> course = courseRepository.findBySlugTitleContaining(slugTitle);
        if(course.isPresent()){
            logInfo(serviceName,"Found course with slug title %s",slugTitle);
            return course.get();
        }
        logInfo(serviceName,"Error in fetching course with id %s",slugTitle);
        return null;
    }

    @Override
    public CreateCourseDTO createCourse(String lang, String chn, CreateCourseDTO dto) {
        logInfo(serviceName,"Start process of creating new course: %s",dto.getSlugTitle());
        try{
            if(findCourseBySlugTitle(dto.getSlugTitle()) == null){
                Course course = modelMapper.map(dto,Course.class);
                courseRepository.save(course);
                logInfo(serviceName,"creating course is completed | %s",dto.getSlugTitle());
                return dto;
            }else{
                logError(serviceName,"Course is already available with the same slug title");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        throw new CustomException("Course is already available with the same slug title", INTERNAL_SERVER_ERROR.value(),serviceName);
    }

    @Override
    public CourseDTO updateCourse(CourseDTO dto) {
        logInfo(serviceName,"Started update process for course with slug title %s",dto.getSlugTitle());
        try{
            final Course courseBySlugTitle = findCourseBySlugTitle(dto.getSlugTitle());
            if(courseBySlugTitle != null){
                Course course = modelMapper.map(dto,Course.class);
                course.setId(courseBySlugTitle.getId());
                courseRepository.save(course);
                logInfo(serviceName,"Updating course is completed | %s",dto.getSlugTitle());
                return dto;
            }else{
                logError(serviceName,"Course not found with the slug title "+dto.getSlugTitle());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        throw new CustomException("Error in saving course with slug title : "+dto.getSlugTitle(), INTERNAL_SERVER_ERROR.value(),serviceName);
    }

    @Override
    public void deleteCourse(Long id) {
        logInfo(serviceName,"Started delete process for course with id %d",id);
        try{
            courseRepository.deleteById(id);
            logInfo(serviceName,"deleted course successfully with id : "+id);
            return;
        }catch(Exception e){
            e.printStackTrace();
        }
        throw new CustomException("Error in deleting course with id : "+id, INTERNAL_SERVER_ERROR.value(),serviceName);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        logInfo(serviceName,"Started a process to getting all courses");
        return AbstractConverterUtil.convertToDTOList(ImmutableList.copyOf(courseRepository.findAll()),CourseDTO.class);
    }

    @Override
    public CourseDTO updateCourseSubject(CoursesBySubjectDTO subject) {
        logInfo(serviceName,"Started adding courses to subject "+subject.getCode());
        final CourseSubject courseSubjectByCode = courseSubjectService.findCourseSubjectByCode(subject.getCode());
        final List<Course> courses = AbstractConverterUtil.convertToEntityList(Collections.singletonList(subject.getCourses()), Course.class);
        courseSubjectByCode.setCourses((Set<Course>) courses);
        Course courseBySlugTitle = findCourseBySlugTitle(slugTitle);
        try{
            if(courseBySlugTitle != null){
                Set<CourseSubject> courseSubjects = getCourseSubjects(subjects);
                courseBySlugTitle.setSubjects(courseSubjects);
                courseRepository.save(courseBySlugTitle);
            }else{
                logError(serviceName,"Course is not found with the slug title "+slugTitle);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        throw new CustomException("Error in saving course subject for subjects : "+subjects.toString(), INTERNAL_SERVER_ERROR.value(),serviceName);
    }

    @Override
    public Set<CourseSubject> getCourseSubjects(Set<String> codes){
        return codes.stream().map(courseSubjectService::findCourseSubjectByCode).collect(Collectors.toSet());
    }
    @Override
    public List<Course> findAllCoursesBySubject(Set<String> subjects) {
        logInfo(serviceName,"Searching all courses with subjects "+subjects.toString());
        Set<CourseSubject> courseSubjects = getCourseSubjects(subjects);
        return ImmutableList.copyOf(courseRepository.findAllBySubjectsIn(courseSubjects));
    }
}
