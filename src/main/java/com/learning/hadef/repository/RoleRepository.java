package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
