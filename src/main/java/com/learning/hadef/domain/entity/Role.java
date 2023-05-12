package com.learning.hadef.domain.entity;

import com.learning.hadef.domain.value.ApplicationUserRole;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "tbl_roles")
@Getter
@Setter
@EqualsAndHashCode
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ApplicationUserRole applicationUserRole;

}
