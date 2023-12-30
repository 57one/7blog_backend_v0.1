package com.wu.blog.pojo;

import java.util.List;

public class noteType {
    private Integer typeId;
    private String typeName;
    private Integer parentId;
    private List<noteType> typeChildren;
    private List<Note> noteChildren;
    private Integer typeSize;

    public noteType(Integer typeId, String typeName, Integer parentId, List<noteType> typeChildren, List<Note> noteChildren, Integer typeSize) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.parentId = parentId;
        this.typeChildren = typeChildren;
        this.noteChildren = noteChildren;
        this.typeSize = typeSize;
    }

    public noteType() {
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<noteType> getTypeChildren() {
        return typeChildren;
    }

    public void setTypeChildren(List<noteType> typeChildren) {
        this.typeChildren = typeChildren;
    }

    public List<Note> getNoteChildren() {
        return noteChildren;
    }

    public void setNoteChildren(List<Note> noteChildren) {
        this.noteChildren = noteChildren;
    }

    public Integer getTypeSize() {
        return typeSize;
    }

    public void setTypeSize(Integer typeSize) {
        this.typeSize = typeSize;
    }
}
