package com.learning.hadef.controller;

import com.learning.hadef.domain.dto.CourseDTO;
import com.learning.hadef.domain.dto.CourseSubjectDTO;
import com.learning.hadef.domain.value.FailureEnum;
import com.learning.hadef.exception.BadRequestException;
import com.learning.hadef.service.CourseSubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.learning.hadef.domain.value.FailureEnum.*;
import static com.learning.hadef.util.LoggingUtil.logError;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/subject")
public class CourseSubjectController {
    private final String serviceName;;
    private final CourseSubjectService courseSubjectService;

    public CourseSubjectController(CourseSubjectService courseSubjectService) {
        this.serviceName = this.getClass().getName();
        this.courseSubjectService = courseSubjectService;
    }

    @PostMapping
    public ResponseEntity<CourseSubjectDTO> createCourseSubject(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestHeader(name = "AUTH") String auth,
            @Valid @RequestBody CourseSubjectDTO dto, BindingResult bindingResult){
        validateBindingResult(bindingResult, FAILED_TO_CREATE_COURSE_SUBJECT);
        CourseSubjectDTO courseSubject = courseSubjectService.createCourseSubject(dto);
        return ResponseEntity.ok(courseSubject);
    }

    @GetMapping
    public ResponseEntity<List<CourseSubjectDTO>> getAllCourses( @RequestHeader(name = "CHN") @Valid String chn,
                                         @RequestHeader(name = "LNG") @Valid String lang,
                                         @RequestHeader(name = "AUTH") String auth){
        List<CourseSubjectDTO> courses = courseSubjectService.getAllCourseSubjects();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{code}")
    public ResponseEntity<CourseSubjectDTO> getCourseByCode(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestHeader(name = "AUTH") String auth,
            @PathVariable("code") @Valid String code){
        CourseSubjectDTO courseSubjectByCode = courseSubjectService.getCourseSubjectByCode(code);
        if(courseSubjectByCode == null){
            logError(serviceName,"Course not found with code: "+code);
            return new ResponseEntity<>(NOT_FOUND);
        }
        return ResponseEntity.ok(courseSubjectByCode);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CourseSubjectDTO> getCourseById(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestHeader(name = "AUTH") String auth,
            @PathVariable @Valid Long id){
        CourseSubjectDTO courseSubjectById = courseSubjectService.getCourseSubjectById(id);
        if(courseSubjectById == null){
            logError(serviceName,"Course not found with id: "+id);
            return new ResponseEntity<>(NOT_FOUND);
        }
        return ResponseEntity.ok(courseSubjectById);
    }

    @PutMapping
    public ResponseEntity<CourseSubjectDTO> updateCourseSubject(@RequestHeader(name = "CHN") @Valid String chn,
                                                  @RequestHeader(name = "LNG") @Valid String lang,
                                                  @RequestHeader(name = "AUTH") String auth,
                                                  BindingResult bindingResult,
                                                  @RequestBody CourseSubjectDTO dto){
        validateBindingResult(bindingResult,FAILED_IN_UPDATE_COURSE);
        CourseSubjectDTO courseSubjectDTO = courseSubjectService.updateCourseSubject(dto);
        return ResponseEntity.ok(courseSubjectDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@RequestHeader(name = "CHN") @Valid String chn,
                                             @RequestHeader(name = "LNG") @Valid String lang,
                                             @RequestHeader(name = "AUTH") String auth,
                                             @PathVariable @Valid Long id){
        courseSubjectService.deleteCourseSubjectById(id);
        return ResponseEntity.ok("Deleted Successfully");
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
