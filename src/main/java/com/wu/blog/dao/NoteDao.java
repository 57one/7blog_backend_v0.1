package com.wu.blog.dao;

import com.wu.blog.pojo.Note;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoteDao {

    //根据id查询笔记
    Note findNoteById(Integer noteId);

    //增加笔记
    Integer addNote(Note note);

    //更新笔记
    Integer updateNote(Note note);

    //根据name查找笔记
    List<Note> findNoteByName(String name);

    //根据id删除笔记
    Integer deleteNoteById(Integer noteId);
}
