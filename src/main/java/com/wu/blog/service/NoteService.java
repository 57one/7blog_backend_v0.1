package com.wu.blog.service;

import com.wu.blog.pojo.Note;

import java.util.List;

public interface NoteService {

    Note findNoteByIdAndConvert(Integer noteId);

    Note findNoteById(Integer noteId);

    Integer addNote(Note note);

    Integer updateNote(Note note);

    List<Note> findNoteByName(String name);

    Integer deleteNoteById(Integer id);
}
