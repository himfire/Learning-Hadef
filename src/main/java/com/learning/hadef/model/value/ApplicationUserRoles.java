package com.learning.hadef.model.value;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.learning.hadef.model.value.ApplicationUserPermission.*;

public enum ApplicationUserRoles {
    Guest(Sets.newHashSet()),
    Student(Sets.newHashSet()),
    Teacher(Sets.newHashSet(TEACHER_READ,TEACHER_UPDATE,TEACHER_WRITE,TEACHER_DELETE)),
    Admin(Sets.newHashSet(ADMIN_READ,ADMIN_UPDATE,ADMIN_WRITE,ADMIN_DELETE)),
    SuperAdmin(Sets.newHashSet());

    private final Set<SimpleGrantedAuthority> permissions;

    ApplicationUserRoles(Set<SimpleGrantedAuthority> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getPermissions() {
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getAuthority()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
