package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Builder
@Table(name = "tbl_employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Employee  extends User implements Serializable {

    private String idCardNo;
    @Embedded
    private Duty duty;
    @OneToMany
    private Set<Department> dept;
}
