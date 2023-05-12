package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_course_ratings")
@EqualsAndHashCode
public class CourseRating {

    @EmbeddedId
    CourseRatingKey id;
    private Long total;
    private Long count;
}
