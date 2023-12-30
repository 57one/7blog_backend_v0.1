package com.wu.blog.service;

import com.wu.blog.pojo.Type;

import java.util.List;

public interface TypeService {
    List<Type> listTypes();

    List<Type> listParentTypes();

    Integer addType(Type type);

    Integer deleteTypeById(Integer Id);

    Integer updateType(Type type);

    Type findTypeById(Integer Id);

    List<Type> findTypesOfPages(Integer begin,Integer size);

    List<Type> findTypesByTypeName(String typeName);

    void updateTypeSize(Type type);
}
