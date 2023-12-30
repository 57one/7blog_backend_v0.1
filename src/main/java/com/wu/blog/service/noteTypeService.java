package com.wu.blog.service;

import com.wu.blog.pojo.noteType;
import java.util.List;

public interface noteTypeService {

    List<noteType> listTypes();

    Integer addNoteType(noteType nType);

    List<noteType> findTypeByName(String typeName);

    noteType findTypesById(Integer id);

    Integer deleteNoteTypeById(Integer id);

    void updateTypeSize(noteType nType);

    noteType findTypeById(Integer typeId);
}
