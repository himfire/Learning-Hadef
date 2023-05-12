package com.learning.hadef.controller;

import com.learning.hadef.domain.dto.CourseDTO;
import com.learning.hadef.domain.dto.CreateCourseDTO;
import com.learning.hadef.domain.entity.Course;
import com.learning.hadef.domain.value.FailureEnum;
import com.learning.hadef.exception.BadRequestException;
import com.learning.hadef.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.learning.hadef.domain.value.FailureEnum.FAILED_IN_UPDATE_COURSE;
import static com.learning.hadef.domain.value.FailureEnum.FAILED_TO_CREATE_COURSE;
import static com.learning.hadef.util.LoggingUtil.logError;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final String serviceName;;
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    public CourseController(CourseService courseService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.serviceName = this.getClass().getName();
        this.courseService = courseService;
    }
    private List<Course> courses = Arrays.asList(Course.builder().titleEN("Introduction to UI/UX").slugTitle("introduction-to-ui-ux").build(),
            Course.builder().titleEN("Introduction to JAVA").slugTitle("introduction-to-java").build(),
            Course.builder().titleEN("Introduction to Python").slugTitle("introduction-to-python").build());
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

    @GetMapping
    public ResponseEntity getAllCourses( @RequestHeader(name = "CHN") @Valid String chn,
                                         @RequestHeader(name = "LNG") @Valid String lang,
                                         @RequestHeader(name = "AUTH") String auth){
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{slug-title}")
    public ResponseEntity<CourseDTO> getCourseBySlugTitle(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestHeader(name = "AUTH") String auth,
            @PathVariable("slug-title") @Valid String slugTitle){
        CourseDTO dto = courseService.getCourseBySlugTitle(slugTitle);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CourseDTO> getCourseById(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestHeader(name = "AUTH") String auth,
            @PathVariable @Valid Long id){
        CourseDTO dto = courseService.getCourseById(id);
        if(dto == null){
            logError(serviceName,"Course not found with id: "+id);
            return new ResponseEntity<>(NOT_FOUND);
        }
        return ResponseEntity.ok(dto);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@RequestHeader(name = "CHN") @Valid String chn,
                                        @RequestHeader(name = "LNG") @Valid String lang,
                                        @RequestHeader(name = "AUTH") String auth,
                                        @PathVariable @Valid Long id){
        courseService.deleteCourse(id);
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
