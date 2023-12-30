package com.wu.blog.service;

import com.wu.blog.dao.TypeDao;
import com.wu.blog.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    @Transactional
    public List<Type> listTypes() {
        return typeDao.findAllTypes();
    }

    @Override
    @Transactional
    public List<Type> listParentTypes() {
        return typeDao.findAllParentTypes();
    }

    @Override
    @Transactional
    public Integer addType(Type type) {
        return typeDao.addType(type);
    }

    @Override
    @Transactional
    public Integer deleteTypeById(Integer Id) {
        return typeDao.deleteTypeById(Id);
    }

    @Override
    @Transactional
    public Integer updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Override
    @Transactional
    public Type findTypeById(Integer Id) {
        return typeDao.findTypeById(Id);
    }

    @Override
    @Transactional
    public List<Type> findTypesOfPages(Integer begin, Integer size) {
        return typeDao.findTypesOfPages(begin,size);
    }

    @Override
    @Transactional
    public List<Type> findTypesByTypeName(String typeName) {
        return typeDao.findTypesByTypeName(typeName);
    }

    @Override
    public void updateTypeSize(Type type) {
        typeDao.updateTypeSize(type);
    }
}
