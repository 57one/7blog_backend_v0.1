package com.wu.blog.controller;

import com.wu.blog.pojo.Sentence;
import com.wu.blog.service.SentenceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class SentenceController {

    @Autowired
    SentenceService sentenceService;

    @ApiOperation(value = "随机返回10条句子",notes="随机返回10条句子")
    @GetMapping("/sentences")
    public List<Sentence> getRandomSentences(){
        return sentenceService.getRandomSentences();
    }

    @ApiOperation(value = "增加句子",notes = "增加句子")
    @PostMapping("/sentences")
    public Map<String,String> addSentence(@RequestBody String content){
        Map<String,String> map=new HashMap<>();
        Sentence sentence=sentenceService.findSentenceByContent(content);
        if(sentence!=null){
            map.put("status","fail");
            map.put("messagq","已经存在同样的句子");
        }else {
            Sentence s=new Sentence();
            s.setContent(content);
            Integer i=sentenceService.addSentence(s);
            if(i==null){
                map.put("status","fail");
                map.put("message","新增句子失败");
            }else {
                map.put("status","success");
                map.put("message","新增句子成功");
            }
        }
        return map;
    }
}
