package com.wu.blog.pojo;

public class Sentence {
    private Integer sentenceId; //句子id
    private String content; //居中内容

    public Sentence(Integer sentenceId, String content) {
        this.sentenceId = sentenceId;
        this.content = content;
    }

    public Sentence() {
    }

    public Integer getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(Integer sentenceId) {
        this.sentenceId = sentenceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
