package com.wu.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.wu.blog.pojo.Diary;
import com.wu.blog.service.DiaryService;
import com.wu.blog.utils.JWTUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @ApiOperation(value = "分压查询日记",notes = "分页查询日记")
    @GetMapping("/diaries/{pageSize}/{size}")
    public List<Diary> findDiariesOfPages(@PathVariable("pageSize")Integer pageSize, @PathVariable("size")Integer size, HttpServletRequest request){
        String token=request.getHeader("diaryToken");
        try {
            JWTUtils.verify(token); //验证令牌
            Integer begin=Integer.valueOf((pageSize-1)*size);
            return diaryService.findDiariesOfPages(begin,size);
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
        } catch (TokenExpiredException e) {
            e.printStackTrace();
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Diary>();
    }

    @ApiOperation(value = "查询日记总数",notes="查询日记总数")
    @GetMapping("/diaries/nums")
    public Integer findDiaryNum() {
        return diaryService.findDiaryNum();
    }

    @ApiOperation(value = "删除日记",notes="删除日记")
    @DeleteMapping("/diary/{diaryId}")
    public void deleteBlogById(@PathVariable("diaryId")Integer diaryId) {
        diaryService.deleteDiaryById(diaryId);
    }

    @ApiOperation(value = "增加日记",notes = "增加日记")
    @PostMapping("/diary")
    public Map<String,String> addDiary(@RequestBody String content) {
        Map<String,String> map=new HashMap<>();
        Diary diary=new Diary();
        JSONObject object= JSONObject.parseObject(content);
        String diaryContent=object.getString("diaryContent");
        diary.setContent(diaryContent);
        Integer i = diaryService.addDiary(diary);
        if(i==null) {
            map.put("status","fail");
            map.put("message","新增日记失败");
        }else {
            map.put("status", "success");
            map.put("message", "新增日记成功");
        }
        return map;
    }

    @ApiOperation(value = "修改日记",notes = "修改日记")
    @PutMapping("/diary")
    public Map<String,String> updateDiary(@RequestBody Diary diary) {
        Map<String,String> map=new HashMap<>();
        Integer i = diaryService.updateDiary(diary);
        if(i==null) {
            map.put("status","fail");
            map.put("message","修改日记失败");
        }else {
            map.put("status", "success");
            map.put("message", "修改日记成功");
        }
        return map;
    }

    @ApiOperation(value = "根据id查找日记",notes = "根据id查找日记")
    @GetMapping("/diary/{diaryId}")
    public Diary getDiaryById(@PathVariable("diaryId")Integer diaryId) {
        return diaryService.findDiaryById(diaryId);
    }
}
