package com.wu.blog.pojo;

import java.util.List;

public class Type {
    private Integer typeId;
    private String typeName;
    private Integer parentId;
    //需要用到父类的name 仔细想想 使用了直接查找父类的名称
//    private Type parentType;
    private List<Type> children;
    private Integer typeSize;

    public Type(Integer typeId, String typeName, Integer parentId, List<Type> children, Integer typeSize) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.parentId = parentId;
        this.children = children;
        this.typeSize = typeSize;
    }

    public Type() {
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

    public List<Type> getChildren() {
        return children;
    }

    public void setChildren(List<Type> children) {
        this.children = children;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getTypeSize() {
        return typeSize;
    }

    public void setTypeSize(Integer typeSize) {
        this.typeSize = typeSize;
    }
}
