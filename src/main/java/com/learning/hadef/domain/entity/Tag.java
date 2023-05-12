package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_courses_tags")
@EqualsAndHashCode
public class Tag extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "tbl_courses_tabs",
            joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id",referencedColumnName = "id"))
    private Set<Course> courses;

}
