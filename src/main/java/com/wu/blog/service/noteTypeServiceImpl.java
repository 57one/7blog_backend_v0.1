package com.wu.blog.service;

import com.wu.blog.dao.noteTypeDao;
import com.wu.blog.pojo.noteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class noteTypeServiceImpl implements noteTypeService {

    @Autowired
    noteTypeDao noteTDao;

    @Override
    @Transactional
    public List<com.wu.blog.pojo.noteType> listTypes() {
        return noteTDao.findAllTypes();
    }

    @Override
    @Transactional
    public Integer addNoteType(noteType nType) {
        return noteTDao.addNoteType(nType);
    }

    @Override
    @Transactional
    public List<noteType> findTypeByName(String typeName) {
        return noteTDao.findNoteTypeByName(typeName);
    }

    @Override
    @Transactional
    public noteType findTypesById(Integer id) {
        return noteTDao.findTypesById(id);
    }

    @Override
    public Integer deleteNoteTypeById(Integer id) {
        return noteTDao.deleteNoteTypeById(id);
    }

    @Override
    public void updateTypeSize(noteType nType) {
        noteTDao.updateTypeSize(nType);
    }

    @Override
    public noteType findTypeById(Integer typeId) {
        return noteTDao.findTypeById(typeId);
    }


}
