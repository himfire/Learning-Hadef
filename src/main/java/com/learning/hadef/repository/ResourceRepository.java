package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Resource;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepository extends CrudRepository<Resource,Long> {
}
