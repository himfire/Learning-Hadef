package com.learning.hadef.repository;

import com.learning.hadef.domain.entity.Language;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LanguageRepository extends CrudRepository<Language,Long> {
    Optional<Language> findLanguageByCode(String code);
}
