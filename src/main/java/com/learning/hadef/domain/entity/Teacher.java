package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Builder
@Table(name = "tbl_teachers",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),@UniqueConstraint(columnNames ={"email"})
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Teacher extends User implements Serializable {
        @OneToMany
        private Set<Education> education;
        @ManyToMany(targetEntity = Subscription.class)
        @JoinTable(name = "tbl_teacher_subscription",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "subscription_id", referencedColumnName = "id")
        )
        private Set<Subscription> subscription;
        @ManyToMany(targetEntity = Course.class)
        @JoinTable(name = "tbl_teacher_courses",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
        )
        private Set<Course> course;
        @OneToMany
        private Set<Experience> experience;
        private String cv;
}
