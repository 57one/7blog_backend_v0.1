package com.wu.blog.service;

import com.wu.blog.pojo.Diary;

import java.util.List;

public interface DiaryService {

    List<Diary> findDiariesOfPages(Integer begin,Integer size);

    Integer findDiaryNum();

    void deleteDiaryById(Integer id);

    Integer addDiary(Diary diary);

    Integer updateDiary(Diary diary);

    Diary findDiaryById(Integer diaryId);
}
