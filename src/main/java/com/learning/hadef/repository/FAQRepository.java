package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.FAQ;
import org.springframework.data.repository.CrudRepository;

public interface FAQRepository extends CrudRepository<FAQ,Long> {
}
