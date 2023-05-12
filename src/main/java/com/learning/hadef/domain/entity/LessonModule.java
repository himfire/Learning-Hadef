package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_lessons_module")
@EqualsAndHashCode
public class LessonModule {

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
    private String descriptionAR;
    @Lob
    private String descriptionEN;
    @OneToMany(mappedBy ="lessonsModule",fetch = FetchType.LAZY,orphanRemoval = true)
    private Set<Lesson> lessons;
    @ManyToOne
    private Course course;


}
