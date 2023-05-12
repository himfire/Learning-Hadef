package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Forum;
import org.springframework.data.repository.CrudRepository;

public interface ForumRepository extends CrudRepository<Forum,Long> {
}
