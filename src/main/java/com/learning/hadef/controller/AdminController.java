package com.learning.hadef.controller;

import com.learning.hadef.model.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin/api/v1/auth")
public class AdminController {

    private static final List<String> user = Arrays.asList("Hisham","Ahmed","Khaled");

}
