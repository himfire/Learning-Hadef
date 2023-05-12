package com.learning.hadef.controller;

import com.learning.hadef.domain.dto.SignUpDTO;
import com.learning.hadef.domain.dto.UserSearchDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @GetMapping
    public String hello(){
        return "Hello Employee";
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
}
