package com.wu.blog.dao;

import com.wu.blog.pojo.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DiaryDao {

    //分页查找日记
    List<Diary> findDiariesOfPages(@Param("begin")Integer begin,@Param("size")Integer size);

    //查询日记数目
    Integer findDiaryNum();

    //根据日记删除日记 啊喂 不会真有人会删除自己的日记吧
    void deleteDiaryById(Integer diaryId);

    //增加日记
    Integer addDiary(Diary diary);

    //根据id修改日记
    Integer updateDiary(Diary diary);

    //根据id查找日记
    Diary findDiaryById(Integer diaryId);
}
