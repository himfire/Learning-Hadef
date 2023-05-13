package com.learning.hadef.service;

import com.learning.hadef.domain.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

public abstract class AbstractService<T extends AbstractEntity> {

    private final JpaRepository repository;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public AbstractService(JpaRepository repository) {
        this.repository = repository;
    }

    public T getById(Long id) throws Throwable {
        return (T) repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

}
