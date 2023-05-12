package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Administrator;
import org.springframework.data.repository.CrudRepository;

public interface AdministratorRepository extends CrudRepository<Administrator,Long> {
}
