package com.learning.hadef.service.impl;

import com.learning.hadef.domain.dto.LoginDTO;
import com.learning.hadef.domain.dto.SignUpDTO;
import com.learning.hadef.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseEntity<String> SignUp(SignUpDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<String> Login(LoginDTO dto) {
        return null;
    }
}
