package com.learning.hadef.service.impl;

import com.learning.hadef.domain.entity.Role;
import com.learning.hadef.repository.RoleRepository;
import com.learning.hadef.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return null;
    }

    @Override
    public void save(Set<Role> roles) {

    }

    @Override
    public Role findByAuthority(String authority) {
        return null;
    }
}
