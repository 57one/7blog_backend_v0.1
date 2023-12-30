package com.wu.blog.service;

import com.wu.blog.dao.DiaryDao;
import com.wu.blog.pojo.Diary;
import com.wu.blog.utils.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService{

    @Autowired
    DiaryDao diaryDao;

    @Override
    public List<Diary> findDiariesOfPages(Integer begin, Integer size) {
        List<Diary> diariesOfPages = diaryDao.findDiariesOfPages(begin, size);
        for(Diary item:diariesOfPages) {
            item.setContent(MarkdownUtils.markdownToHtmlExtensions(item.getContent()));
        }
        return diariesOfPages;
    }

    @Override
    public Integer findDiaryNum() {
        return diaryDao.findDiaryNum();
    }

    @Override
    public void deleteDiaryById(Integer id) {
        diaryDao.deleteDiaryById(id);
    }

    @Override
    public Integer addDiary(Diary diary) {
        Date date=new Date();
        diary.setCreateTime(date);
        diary.setUpdateTime(date);
        return diaryDao.addDiary(diary);
    }

    @Override
    public Integer updateDiary(Diary diary) {
        Date date=new Date();
        diary.setUpdateTime(date);
        return diaryDao.updateDiary(diary);
    }

    @Override
    public Diary findDiaryById(Integer diaryId) {
        return diaryDao.findDiaryById(diaryId);
    }
}
