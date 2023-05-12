package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription,Long> {
}
