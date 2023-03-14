package com.learning.hadef.model.entity;

import com.learning.hadef.model.value.ApplicationUserRoles;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sys_users")
@Getter
@Setter
public class User extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    @Embedded
    private Phone phone;
    @Embedded
    private Address address;
    private boolean isEnabled;
    private boolean isNotLocked;
    private String profileImageUrl;
    private String[] applicationUserRoles;
    private String[] applicationUserPermission;
    private String description;
    private LocalDateTime lastLoginDate;
    private LocalDateTime lastLoginDateDisplay;
    private LocalDateTime joinedDate;
    private String loginIP;
}
