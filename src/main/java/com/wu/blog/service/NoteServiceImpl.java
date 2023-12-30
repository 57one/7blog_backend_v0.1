package com.wu.blog.service;

import com.wu.blog.dao.NoteDao;
import com.wu.blog.pojo.Note;
import com.wu.blog.utils.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteDao noteDao;

    @Override
    @Transactional
    public Note findNoteByIdAndConvert(Integer noteId) {
        Note note=noteDao.findNoteById(noteId);
        note.setContent(MarkdownUtils.markdownToHtmlExtensions(note.getContent()));
        return note;
    }

    @Override
    @Transactional
    public Note findNoteById(Integer noteId) {
        return noteDao.findNoteById(noteId);
    }

    @Override
    @Transactional
    public Integer addNote(Note note) {
        Date date=new Date();
        note.setCreateTime(date);
        note.setUpdateTime(date);
        return noteDao.addNote(note);
    }

    @Override
    @Transactional
    public Integer updateNote(Note note) {
        note.setUpdateTime(new Date());
        return noteDao.updateNote(note);
    }

    @Override
    public List<Note> findNoteByName(String name) {
       return noteDao.findNoteByName(name);
    }

    @Override
    public Integer deleteNoteById(Integer id) {
        return noteDao.deleteNoteById(id);
    }

}
