package com.learning.hadef.domain.dto;

import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseSubjectDTO {

    private Long id;
    private String nameAR;
    private String nameEN;
    private String code;
    private Set<CourseDTO> courses;
}
