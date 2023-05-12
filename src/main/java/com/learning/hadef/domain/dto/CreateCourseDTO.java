package com.learning.hadef.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCourseDTO {
    @NotNull(message = "4001 - title should not be null")
    @NotEmpty(message = "Arabic Title should not be empty")
    @JsonProperty(value ="title-ar")
    private String titleAR;
    @NotNull(message = "4001 - title should not be null")
    @NotEmpty(message = "English Title should not be empty")
    @JsonProperty(value ="title-en")
    private String titleEN;
    @NotNull(message = "4002 - slug title should not be null")
    @NotEmpty(message = "Error in saving slug title")
    @JsonProperty(value ="slug-title")
    private String slugTitle;
    @Lob
    @NotNull(message = "4003 - description should not be null")
    @NotEmpty(message = "Arabic Course description must be provided")
    @JsonProperty(value ="description-ar")
    private String descriptionAR;
    @Lob
    @NotNull(message = "4003 - description should not be null")
    @NotEmpty(message = "English Course description must be provided")
    @JsonProperty(value ="description-en")
    private String descriptionEN;

    @NotNull(message = "4004 - course image should not be null")
    @NotEmpty(message = "course image must be provided")
    private String courseImageURL;
    @JsonFormat(timezone = "GMT+3", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(timezone = "GMT+3", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;
}
