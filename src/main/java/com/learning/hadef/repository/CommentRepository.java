package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long> {
}
