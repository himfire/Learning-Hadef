package com.learning.hadef.domain.entity;

import com.learning.hadef.domain.value.Gender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class User extends AbstractEntity {

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    private Gender gender;
    @Lob
    private String aboutMe;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateOfBirth;
    @Embedded
    private Phone phone;
    @Embedded
    private Address address;
    private String nationality;
    @Lob
    private String avatar;
    private boolean isEnabled;
    private boolean isNotLocked;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginDateDisplay;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinedDate;
    private String loginIP;
    @Column(name = "failed_attempts",length = 4)
    private Long failedLoginAttempts;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
