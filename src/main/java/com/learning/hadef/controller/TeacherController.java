package com.learning.hadef.controller;

import com.learning.hadef.domain.dto.CourseDTO;
import com.learning.hadef.domain.dto.CreateCourseDTO;
import com.learning.hadef.domain.value.FailureEnum;
import com.learning.hadef.exception.BadRequestException;
import com.learning.hadef.service.CourseService;
import com.learning.hadef.service.CourseSubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.stream.Collectors;

import static com.learning.hadef.domain.value.FailureEnum.FAILED_IN_UPDATE_COURSE;
import static com.learning.hadef.domain.value.FailureEnum.FAILED_TO_CREATE_COURSE;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {
    private final CourseSubjectService courseSubjectService;
    private final CourseService courseService;
    private final String serviceName;

    public TeacherController(CourseSubjectService courseSubjectService, CourseService courseService) {
        this.courseSubjectService = courseSubjectService;
        this.courseService = courseService;
        this.serviceName = this.getClass().getName();
    }

    @PostMapping()
    public ResponseEntity<CreateCourseDTO> createCourse(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestHeader(name = "AUTH") String auth,
            @Valid @RequestBody CreateCourseDTO dto, BindingResult bindingResult){
        validateBindingResult(bindingResult, FAILED_TO_CREATE_COURSE);
        CreateCourseDTO response = courseService.createCourse(lang, chn, dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CourseDTO> updateCourse(@RequestHeader(name = "CHN") @Valid String chn,
                                                  @RequestHeader(name = "LNG") @Valid String lang,
                                                  @RequestHeader(name = "AUTH") String auth,
                                                  BindingResult bindingResult,
                                                  @RequestBody CourseDTO dto){
        validateBindingResult(bindingResult,FAILED_IN_UPDATE_COURSE);
        CourseDTO courseDTO = courseService.updateCourse(dto);
        return ResponseEntity.ok(courseDTO);
    }

    private void validateBindingResult(BindingResult bindingResult, FailureEnum failureEnum){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","))
                    ,failureEnum,serviceName);
        }
    }
}
