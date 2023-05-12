package com.learning.hadef.service;

import com.learning.hadef.domain.entity.Role;

import java.util.Set;

public interface RoleService {

    Role saveRole(Role role);
    void save(Set<Role> roles);
    Role findByAuthority(String authority);
}
