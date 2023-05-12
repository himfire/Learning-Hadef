package com.learning.hadef.controller;

import com.learning.hadef.domain.dto.LoginDTO;
import com.learning.hadef.domain.dto.SignUpDTO;
import com.learning.hadef.domain.value.FailureEnum;
import com.learning.hadef.exception.BadRequestException;
import com.learning.hadef.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.stream.Collectors;

import static com.learning.hadef.domain.value.FailureEnum.FAILED_TO_SIGNUP_TEACHER;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final String serviceName;

    public AuthController(AuthService authService) {
        this.authService = authService;
        this.serviceName = this.getClass().getName();
    }

    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestHeader(name = "AUTH") String auth,
            @RequestHeader(name = "ROLE") String role,
            @Valid @RequestBody LoginDTO dto, BindingResult bindingResult
    ){
        return ResponseEntity.ok("login");
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestHeader(name = "AUTH") String auth,
            @RequestHeader(name = "ROLE") String role,
            @Valid @RequestBody SignUpDTO dto, BindingResult bindingResult){
        validateBindingResult(bindingResult, FAILED_TO_SIGNUP_TEACHER);
        return ResponseEntity.ok("employeeService.signUp(dto)");
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
