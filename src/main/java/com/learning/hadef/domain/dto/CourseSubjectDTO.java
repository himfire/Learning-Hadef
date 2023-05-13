package com.learning.hadef.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseSubjectDTO {

    private Long id;
    @JsonProperty(value ="name-ar")
    private String nameAR;
    @JsonProperty(value ="name-en")
    private String nameEN;
    private String code;
    private Set<CourseDTO> courses;
}
