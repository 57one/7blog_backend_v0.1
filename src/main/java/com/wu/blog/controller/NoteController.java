package com.wu.blog.controller;

import com.wu.blog.pojo.Common;
import com.wu.blog.pojo.Note;
import com.wu.blog.pojo.noteType;
import com.wu.blog.service.CommonService;
import com.wu.blog.service.NoteService;
import com.wu.blog.service.noteTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    CommonService commonService;

    @Autowired
    noteTypeService noteTService;

    @ApiOperation(value = "根据id查询具体笔记",notes = "根据id查询具体笔记")
    @GetMapping("/note/{noteId}")
    public Note findNoteById(@PathVariable("noteId") Integer noteId) {
        return noteService.findNoteById(noteId);
    }

    @ApiOperation(value = "根据id查询具体笔记并转换",notes = "根据id查询具体笔记并转换")
    @GetMapping("/notes/{noteId}")
    public Note findNoteByIdAndConvert(@PathVariable("noteId") Integer noteId) {
        return noteService.findNoteByIdAndConvert(noteId);
    }

    @ApiOperation(value = "增加笔记",notes = "增加笔记")
    @PostMapping("/notes")
    public Map<String,String> addNote(@RequestBody Note note) {
        Map<String,String> map=new HashMap<>();
        List<Note> noteList=noteService.findNoteByName(note.getNoteName());
        for(Note n:noteList) {
            if(n.getTypeId()==note.getTypeId()) {
                map.put("status","fail");
                map.put("message","不能在同类下增加相同的笔记");
                return map;
            }
        }
        Integer i=noteService.addNote(note);
        if(i==null) {
            map.put("status","fail");
            map.put("message","新增笔记失败");
        }else {
            map.put("status","success");
            map.put("message","新增笔记成功");
            Common c = commonService.findCommon();
            Common common=new Common();
            common.setNotes(c.getNotes()+1);
            commonService.updataCommon(common);

            //文件的数量加一
            Integer typeId = note.getTypeId();
            noteType nType = noteTService.findTypeById(typeId);
            while (nType!=null) {
                Integer typeSize = nType.getTypeSize()+1;
                nType.setTypeSize(typeSize);
                noteTService.updateTypeSize(nType);
                typeId=nType.getParentId();
                nType=noteTService.findTypeById(typeId);
            }
        }
        return map;
    }

    @ApiOperation(value = "修改笔记",notes = "修改笔记")
    @PutMapping("/notes")
    public Map<String,String> updateNote(@RequestBody Note note) {
        Map<String,String> map=new HashMap<>();
        Integer i=noteService.updateNote(note);
        if(i==null){
            map.put("status","fail");
            map.put("message","更新笔记失败");
        }else {
            map.put("status","success");
            map.put("message","更新笔记成功");
        }
        return map;
    }

    @ApiOperation(value="删除笔记",notes = "删除笔记")
    @DeleteMapping("/notes/{noteId}")
    public Map<String,String> deleteNote(@PathVariable("noteId")Integer noteId) {
        //文件的数量减一
        Note note=noteService.findNoteById(noteId);
        Integer typeId = note.getTypeId();
        noteType nType = noteTService.findTypeById(typeId);
        while (nType!=null) {
            Integer typeSize = nType.getTypeSize()-1;
            nType.setTypeSize(typeSize);
            noteTService.updateTypeSize(nType);
            typeId=nType.getParentId();
            nType=noteTService.findTypeById(typeId);
        }
        Map<String,String> map=new HashMap<>();
        Integer i=noteService.deleteNoteById(noteId);
        if(i==null){
            map.put("status","fail");
            map.put("message","删除笔记失败");
        }else {
            map.put("status","success");
            map.put("message","删除笔记成功");
            Common c = commonService.findCommon();
            Common common=new Common();
            common.setNotes(c.getNotes()-1);
            commonService.updataCommon(common);
        }
        return map;
    }
}