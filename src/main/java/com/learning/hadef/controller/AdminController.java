package com.learning.hadef.controller;

import com.learning.hadef.domain.dto.*;
import com.learning.hadef.domain.entity.CourseSubject;
import com.learning.hadef.domain.value.FailureEnum;
import com.learning.hadef.exception.BadRequestException;
import com.learning.hadef.service.CourseService;
import com.learning.hadef.service.CourseSubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import java.util.Set;
import java.util.stream.Collectors;

import static com.learning.hadef.domain.value.FailureEnum.FAILED_TO_CREATE_COURSE;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final CourseSubjectService courseSubjectService;
    private final CourseService courseService;
    private final String serviceName;

    public AdminController(CourseSubjectService courseSubjectService, CourseService courseService) {
        this.courseSubjectService = courseSubjectService;
        this.courseService = courseService;
        this.serviceName = this.getClass().getName();
    }
    @PutMapping("/subject/${slug-title}")
    public ResponseEntity<CourseSubject> addCoursesToSubject(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestHeader(name = "AUTH") String auth,
            @Valid @RequestBody CoursesBySubjectDTO coursesBySubjectDTO,
            BindingResult bindingResult){
        validateBindingResult(bindingResult, FAILED_TO_CREATE_COURSE);
        courseService.addCoursesToCourseSubject(coursesBySubjectDTO);
    }

    @GetMapping("/username")
    public ResponseEntity getByUsername(@RequestBody UserSearchDTO username){
        return ResponseEntity.ok("userService.getUserByUsername(username)");
    }

    @GetMapping("/email")
    public ResponseEntity getByEmail(@PathVariable UserSearchDTO email){
        return ResponseEntity.ok("userService.getUserByEmail(email)");
    }

    @GetMapping("/id-card")
    public ResponseEntity getByIdCardNo(@RequestBody UserSearchDTO cardNo){
        return ResponseEntity.ok("userService.getUserByUsername(username)");
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
