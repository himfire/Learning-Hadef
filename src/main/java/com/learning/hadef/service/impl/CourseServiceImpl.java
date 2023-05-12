package com.learning.hadef.service.impl;

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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.learning.hadef.util.LoggingUtil.logError;
import static com.learning.hadef.util.LoggingUtil.logInfo;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

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
        logInfo(serviceName,"Started update process for course with slug title %d",id);
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            logInfo(serviceName,"Found course with slug title %d",id);
            CourseDTO dto = modelMapper.map(course.get(),CourseDTO.class);
            return dto;
        }
        return null;
    }

    @Override
    public CourseDTO getCourseBySlugTitle(String slugTitle) {
        logInfo(serviceName,"Started update process for course with slug title %s",slugTitle);
        Optional<Course> course = courseRepository.findBySlugTitleContaining(slugTitle);
        if(course.isPresent()){
            logInfo(serviceName,"Found course with slug title %s",slugTitle);
            CourseDTO dto = modelMapper.map(course.get(),CourseDTO.class);
            return dto;
        }
        return null;
    }

    @Override
    public CreateCourseDTO createCourse(String lang, String chn, CreateCourseDTO dto) {
        logInfo(serviceName,"Start process of creating new course: %s",dto.getSlugTitle());
        try{
            if(courseRepository.findBySlugTitleContaining(dto.getSlugTitle()).isPresent()){
                logError(serviceName,"Course is already available with the same slug title");
                throw new CustomException("Course is already available with the same slug title", NOT_ACCEPTABLE.value(),serviceName);
            }
            Course course = modelMapper.map(dto,Course.class);
            courseRepository.save(course);
        }catch(Exception e){
            throw new CustomException("Course is already available with the same slug title", INTERNAL_SERVER_ERROR.value(),serviceName);
        }
        logInfo(serviceName,"creating course is completed | %s",dto.getSlugTitle());
        return dto;
    }

    @Override
    public CourseDTO updateCourse(CourseDTO dto) {
        logInfo(serviceName,"Started update process for course with slug title %s",dto.getSlugTitle());
        try{
            Optional<Course> bySlugTitleContaining = courseRepository.findBySlugTitle(dto.getSlugTitle());
            if(bySlugTitleContaining.isPresent()){
                Course course = bySlugTitleContaining.get();
                logInfo(serviceName,"Found course with slug title : "+course.getSlugTitle());
                Course updatedCourse = modelMapper.map(dto,Course.class);
                updatedCourse.setId(course.getId());
                courseRepository.save(updatedCourse);
                return dto;
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
        List<CourseDTO> all = ImmutableList.copyOf(courseRepository.findAll()).stream()
                .map(c -> modelMapper.map(c, CourseDTO.class))
                .collect(Collectors.toList());
        return all;
    }

    @Override
    public List<CourseDTO> getAllCoursesBySubject(String subject) {
        List<CourseDTO> all = ImmutableList.copyOf(courseRepository.findAllBySubjects(subject)).stream()
                .map(c -> modelMapper.map(c, CourseDTO.class))
                .collect(Collectors.toList());
        return all;
    }

    @Override
    public CourseDTO updateCourseSubject(Set<String> subjects, String slugTitle) {
        logInfo(serviceName,"Started update process for course with slug title %s",slugTitle);
        List<CourseSubjectDTO> courseSubjects = subjects.stream().map(subject ->
            courseSubjectService.getCourseSubjectByCode(subject)
        ).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(courseSubjects)){
            courseSubjects.stream().map(
                    courseSubject -> courseSubjectService.get(courseSubject.getCode())
            ).collect(Collectors.toList());
//            try{
//                Optional<Course> bySlugTitleContaining = courseRepository.findBySlugTitle(slugTitle);
//                if(bySlugTitleContaining.isPresent()){
//                    Course course = bySlugTitleContaining.get();
//
//                    course.setSubjects(courseSubjects);
//                    courseRepository.save(updatedCourse);
//                    return updatedCourse;
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            throw new CustomException("Error in saving course with slug title : "+dto.getSlugTitle(), INTERNAL_SERVER_ERROR.value(),serviceName);
        }else{
            throw new CustomException("Error in finding the courses subjects", INTERNAL_SERVER_ERROR.value(),serviceName);
        }
        return null;
    }

    @Override
    public List<CoursesBySubjectDTO> getAllCoursesBySubject(Set<String> subject) {
        return null;
    }

}
