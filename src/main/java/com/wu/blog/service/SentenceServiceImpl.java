package com.wu.blog.service;

import com.wu.blog.dao.SentenceDao;
import com.wu.blog.pojo.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentenceServiceImpl implements SentenceService{

    @Autowired
    SentenceDao sentenceDao;

    @Override
    public Integer findSentencsNum() {
        return sentenceDao.findSentencesNum();
    }

    @Override
    public List<Sentence> getRandomSentences() {
        return sentenceDao.getRandomSentences();
    }


    @Override
    public Integer addSentence(Sentence sentence) {
        return sentenceDao.addSentence(sentence);
    }

    @Override
    public Sentence findSentenceByContent(String content) {
        return sentenceDao.findSentenceByContent(content);
    }
}
