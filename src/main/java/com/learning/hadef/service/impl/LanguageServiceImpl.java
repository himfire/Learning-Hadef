package com.learning.hadef.service.impl;

import com.learning.hadef.repository.LanguageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl {

    private final LanguageRepository languageRepository;
    private final String serviceName;
    private final ModelMapper modelMapper;

    public LanguageServiceImpl(LanguageRepository languageRepository, ModelMapper modelMapper) {
        this.languageRepository = languageRepository;
        this.serviceName = this.getClass().getName();
        this.modelMapper = modelMapper;
    }
}
