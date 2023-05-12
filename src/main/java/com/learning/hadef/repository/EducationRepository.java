package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Education;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<Education,Long> {
}
