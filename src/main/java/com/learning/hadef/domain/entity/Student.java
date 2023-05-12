package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Builder
@Table(name = "tbl_student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Student extends User implements Serializable {
        @OneToMany
        private Set<Education> education;
        @ManyToMany(targetEntity = Subscription.class)
        @JoinTable(name = "tbl_student_subscription",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "subscription_id", referencedColumnName = "id")
        )
        private Set<Subscription> subscription;
        @ManyToMany(targetEntity = Course.class)
        @JoinTable(name = "tbl_student_courses",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
        )
        private Set<Course> course;
}
