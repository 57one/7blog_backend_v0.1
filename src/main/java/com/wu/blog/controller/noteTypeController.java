package com.wu.blog.controller;

import com.wu.blog.pojo.Note;
import com.wu.blog.pojo.noteType;
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
public class noteTypeController {

    @Autowired
    private noteTypeService noteTService;
    @Autowired
    private NoteService noteService;

    @ApiOperation(value = "查找所有分类",notes = "查找所有分类")
    @GetMapping("/noteTypes")
    public List<noteType> list() {
        return noteTService.listTypes();
    }

    @ApiOperation(value = "添加分类",notes = "添加分类")
    @PostMapping("/types")
    public Map<String,String> addType(@RequestBody noteType nType) {
        Map<String,String> map =new HashMap<>();
        List<noteType> tList =noteTService.findTypeByName(nType.getTypeName());
        for(noteType i:tList) {
            if(i.getParentId()==nType.getParentId()){
                map.put("statue","fail");
                map.put("message","不能在同类下增加相同的分类");
                return map;
            }
        }
        Integer i = noteTService.addNoteType(nType);
        if(i==null) {
            map.put("status","fail");
            map.put("message","新增分类失败");
        }else {
            map.put("status","success");
            map.put("message","新增分类成功");
        }
        return map;
    }

    @ApiOperation(value = "删除分类",notes = "删除分类")
    @DeleteMapping("/noteTypes/{typeId}")
    public Map<String,String> deleteType(@PathVariable("typeId")Integer typeId) {
        Map<String,String> map=new HashMap<>();
        noteType type = noteTService.findTypesById(typeId);
        Integer i=deleteNoteType(type);
        if(i==null){
            map.put("status","fail");
            map.put("message","删除分类失败");
        }else{
            map.put("status","success");
            map.put("message","删除分类成功");
        }
        return map;
    }

    public Integer deleteNoteType(noteType nType) {
        for(noteType t:nType.getTypeChildren()) {
            deleteNoteType(t);
        }
        for(Note n:nType.getNoteChildren()) {
            deleteNote(n);
        }
        return noteTService.deleteNoteTypeById(nType.getTypeId());
    }

    public Integer deleteNote(Note note) {
        return noteService.deleteNoteById(note.getNoteId());
    }
}
