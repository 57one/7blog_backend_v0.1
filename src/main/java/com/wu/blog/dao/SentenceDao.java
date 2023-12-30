package com.wu.blog.dao;

import com.wu.blog.pojo.Sentence;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SentenceDao {

    //查询句子数目
    Integer findSentencesNum();

    //随机获得10条语句
    List<Sentence> getRandomSentences();

    //增加句子
    Integer addSentence(Sentence sentence);

    //根据内容查询句子
    Sentence findSentenceByContent(String content);
}
