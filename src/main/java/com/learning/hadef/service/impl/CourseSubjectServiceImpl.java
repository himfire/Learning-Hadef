package com.learning.hadef.service.impl;

import com.google.common.collect.ImmutableList;
import com.learning.hadef.domain.dto.CourseDTO;
import com.learning.hadef.domain.dto.CourseSubjectDTO;
import com.learning.hadef.domain.entity.Course;
import com.learning.hadef.domain.entity.CourseSubject;
import com.learning.hadef.exception.CustomException;
import com.learning.hadef.repository.CourseSubjectRepository;
import com.learning.hadef.service.CourseSubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.learning.hadef.util.LoggingUtil.logError;
import static com.learning.hadef.util.LoggingUtil.logInfo;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Service
public class CourseSubjectServiceImpl implements CourseSubjectService {
    private final CourseSubjectRepository courseSubjectRepository;
    private final String serviceName;
    private final ModelMapper modelMapper;

    public CourseSubjectServiceImpl(CourseSubjectRepository courseSubjectRepository, ModelMapper modelMapper) {
        this.courseSubjectRepository = courseSubjectRepository;
        this.serviceName = this.getClass().getName();
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourseSubjectDTO> getAllCourseSubjects() {
        logInfo(serviceName,"Started a process to getting all courses");
        List<CourseSubjectDTO> all = ImmutableList.copyOf(courseSubjectRepository.findAll()).stream()
                .map(c -> modelMapper.map(c, CourseSubjectDTO.class))
                .collect(Collectors.toList());
        return all;
    }

    @Override
    public void deleteCourseSubjectById(Long courseSubjectId) {
        logInfo(serviceName,"Started delete process for subject course with id %d",courseSubjectId);
        try{
            courseSubjectRepository.deleteById(courseSubjectId);
            logInfo(serviceName,"deleted subject of courses successfully with id : "+courseSubjectId);
            return;

        }catch(Exception e){
            e.printStackTrace();
        }
        throw new CustomException("Error in deleting subject of courses with id : "+courseSubjectId, INTERNAL_SERVER_ERROR.value(),serviceName);
    }

    @Override
    public void deleteCourseSubject(CourseSubjectDTO courseSubject) {
        logInfo(serviceName,"Started delete process for subject course %s",courseSubject.toString());
        try{
            CourseSubject map = modelMapper.map(courseSubject, CourseSubject.class);
            courseSubjectRepository.delete(map);
            logInfo(serviceName,"deleted subject of courses successfully");
            return;

        }catch(Exception e){
            e.printStackTrace();
        }
        throw new CustomException("Error in deleting subject of courses with %d "+ courseSubject.getId(), INTERNAL_SERVER_ERROR.value(),serviceName);
    }

    @Override
    public CourseSubjectDTO createCourseSubject(CourseSubjectDTO courseSubject) {
        logInfo(serviceName,"Start process of creating new course: %s",courseSubject.getCode());
        try{
            if(courseSubjectRepository.findCourseSubjectByCode(courseSubject.getCode()).isPresent()){
                logError(serviceName,"Course Subject is already available with the same code "+courseSubject.getCode());
                throw new CustomException("Course Subject is already available with the same code "+courseSubject.getCode(), NOT_ACCEPTABLE.value(),serviceName);
            }
            CourseSubject course = modelMapper.map(courseSubject,CourseSubject.class);
            courseSubjectRepository.save(course);
        }catch(Exception e){
            throw new CustomException("Error in saving course subject "+courseSubject.getCode(), INTERNAL_SERVER_ERROR.value(),serviceName);
        }
        logInfo(serviceName,"creating course subject is completed | "+courseSubject.getCode());
        return courseSubject;
    }

    @Override
    public CourseSubjectDTO updateCourseSubject(CourseSubjectDTO courseSubjectDTO) {
        logInfo(serviceName,"Started update subject of courses with code "+courseSubjectDTO.getCode());
        CourseSubjectDTO courseSubjectByTheCode = getCourseSubjectByCode(courseSubjectDTO.getCode());
        return courseSubjectByTheCode;

        try{
            Optional<CourseSubject> courseSubjectByCode = courseSubjectRepository.findCourseSubjectByCode(courseSubjectDTO.getCode());
            if(courseSubjectByCode.isPresent()){
                CourseSubject courseSubject = courseSubjectByCode.get();
                Long id = courseSubject.getId();
                logInfo(serviceName,"Found course subject with code : "+courseSubjectDTO.getCode());
                courseSubject = modelMapper.map(courseSubjectDTO,CourseSubject.class);
                courseSubject.setId(id);
                courseSubjectRepository.save(courseSubject);
                return courseSubjectDTO;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        throw new CustomException("Error in updating course with code "+courseSubjectDTO.getCode(), INTERNAL_SERVER_ERROR.value(),serviceName);
    }

    @Override
    public CourseSubjectDTO getCourseSubjectById(Long id) {
        CourseSubject courseSubject = findCourseSubjectById(id);
        if(courseSubject == null){
            logError(serviceName,"Error in searching for course subject with id "+id);
            return null;
        }
        CourseSubjectDTO dto = modelMapper.map(courseSubject,CourseSubjectDTO.class);
        return dto;
    }

    @Override
    public CourseSubject findCourseSubjectById(Long id) {
        logInfo(serviceName,"Started process for getting course subject with id %d",id);
        Optional<CourseSubject> courseSubject = courseSubjectRepository.findById(id);
        if(courseSubject.isPresent()){
            logInfo(serviceName,"Found course with slug title %d",id);
            return courseSubject.get();
        }
        return null;
    }

    @Override
    public CourseSubjectDTO getCourseSubjectByCode(String code) {
        CourseSubject courseSubject = findCourseSubjectByCode(code);
        if(courseSubject == null){
            logError(serviceName,"Error in searching for course subject "+code);
            return null;
        }
        CourseSubjectDTO dto = modelMapper.map(courseSubject,CourseSubjectDTO.class);
        return dto;
    }

    @Override
    public CourseSubject findCourseSubjectByCode(String code) {
        logInfo(serviceName,"Started process for getting course subject with id %s",code);
        Optional<CourseSubject> courseSubject = courseSubjectRepository.findCourseSubjectByCode(code);
        if(courseSubject.isPresent()){
            logInfo(serviceName,"Found course subject with code %s",code);
            return courseSubject.get();
        }
        return null;
    }
}