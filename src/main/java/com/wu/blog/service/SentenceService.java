package com.wu.blog.service;

import com.wu.blog.pojo.Sentence;

import java.util.List;

public interface SentenceService {

    Integer findSentencsNum();

    List<Sentence> getRandomSentences();

    Integer addSentence(Sentence sentence);

    Sentence findSentenceByContent(String content);
}
