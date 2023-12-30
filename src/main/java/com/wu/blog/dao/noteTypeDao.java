package com.wu.blog.dao;

import com.wu.blog.pojo.Type;
import com.wu.blog.pojo.noteType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface noteTypeDao {

    //找到所有分类
    List<noteType> findAllTypes();

    //添加分类
    Integer addNoteType(noteType nType);

    //根据name查找分类
    List<noteType> findNoteTypeByName(String typeName);

    //根据id查询该分类下所有的分类及文章
    noteType findTypesById(Integer id);

    //根据id删除分类
    Integer deleteNoteTypeById(Integer id);

    void updateTypeSize(noteType nType);

    noteType findTypeById(Integer typeId);
}
