package com.learning.hadef.service;

import com.learning.hadef.domain.dto.LoginDTO;
import com.learning.hadef.domain.dto.SignUpDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<String> SignUp(SignUpDTO dto);
    ResponseEntity<String> Login(LoginDTO dto);
}
