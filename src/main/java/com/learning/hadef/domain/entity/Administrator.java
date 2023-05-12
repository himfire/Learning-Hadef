package com.learning.hadef.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "tbl_admins")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Administrator extends User {
    private String employeeId;
    private String department;
    @Embedded
    private Duty duty;
}
