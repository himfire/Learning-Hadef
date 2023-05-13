package com.learning.hadef.util;

import com.learning.hadef.domain.entity.AbstractEntity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverterUtil<E extends AbstractEntity> {

    public static <E,D> D convertToDTO(E e,Class<D> clazz){
        return new ModelMapper().map(e,clazz);
    }

    public static <E> E convertToEntity(Object obj,Class<E> clazz){
        return new ModelMapper().map(obj,clazz);
    }

    public static <E,D> List<D> convertToDTOList(List<E> list, Class<D> clazz){
        return list.stream().map(e -> convertToDTO(e, clazz)).collect(Collectors.toList());
    }

    public static <E> List<E> convertToEntityList(List<Object> list, Class<E> clazz){
        return list.stream().map(e -> convertToEntity(e,clazz)).collect(Collectors.toList());
    }
}
