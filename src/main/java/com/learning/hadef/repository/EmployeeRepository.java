package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}
