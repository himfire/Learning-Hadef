package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_lessons")
@EqualsAndHashCode
public class Lesson extends Auditable{

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(nullable = false,updatable = false)
    private Long id;
    @Column(unique = true,nullable = false)
    private String uuid = UUID.randomUUID().toString();
    private String titleAR;
    private String titleEN;
    @Lob
    private String contentAR;
    @Lob
    private String contentEN;
    @Column
    private String duration;
    @OneToMany
    private Set<Resource> resources;
    @ManyToOne
    private LessonModule lessonsModule;
    @OneToMany(fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Comment> comments;
}
