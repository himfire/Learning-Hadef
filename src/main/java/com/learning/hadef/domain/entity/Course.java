package com.learning.hadef.domain.entity;

import com.learning.hadef.domain.value.CourseFeeCategory;
import com.learning.hadef.domain.value.CourseLevel;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_courses")
@EqualsAndHashCode
public class Course extends Auditable{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(unique = true,nullable = false)
    private String uuid = UUID.randomUUID().toString();
    private String titleAR;
    private String titleEN;
    @Column(unique = true)
    private String slugTitle;
    @Lob
    private String descriptionAR;
    @Lob
    private String descriptionEN;
    private BigDecimal price;
    private String courseImageURL;
    private String courseVideoURL;
    private String videoImage;
    @Lob
    private String targetAudience;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private Set<FAQ> faq;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private Set<LessonModule> lessonModules;
    private CourseLevel courseLevel;
    private String duration;
    private CourseFeeCategory courseFeeCategory;
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Language> language;
    @ManyToMany(mappedBy = "courses")
    private Set<Tag> tags;
    @OneToMany(targetEntity = Comment.class,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Comment> comments;
    @CollectionTable(name="tbl_courses_contributors")
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> contributors;
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<CourseSubject> subjects;
}
