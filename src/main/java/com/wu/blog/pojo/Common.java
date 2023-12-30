package com.wu.blog.pojo;

public class Common {
    private Integer notes;
    private Integer blogs;
    private Integer likes;
    private String diaryPASD;

    public Common(Integer notes, Integer blogs, Integer likes,String diaryPASD) {
        this.notes = notes;
        this.blogs = blogs;
        this.likes = likes;
        this.diaryPASD=diaryPASD;
    }

    public Common() {
    }

    public Integer getNotes() {
        return notes;
    }

    public void setNotes(Integer notes) {
        this.notes = notes;
    }

    public Integer getBlogs() {
        return blogs;
    }

    public void setBlogs(Integer blogs) {
        this.blogs = blogs;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getDiaryPASD() {
        return diaryPASD;
    }

    public void setDiaryPASD(String diaryPASD) {
        this.diaryPASD = diaryPASD;
    }
}
