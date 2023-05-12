package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_course_subject")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseSubject extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameAR;
    private String nameEN;
    @Column(unique = true)
    private String code;
    @ManyToMany
    @JoinTable(name = "tbl_courses_subjects",
            joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Course> courses;
}