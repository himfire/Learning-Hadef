package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag,Long> {
}
