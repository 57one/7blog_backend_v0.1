package com.wu.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wu.blog.pojo.Common;
import com.wu.blog.service.CommonService;
import com.wu.blog.utils.JWTUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class CommonController {

    @Autowired
    CommonService commonService;

    @ApiOperation(value = "查询Common的值",notes = "查询Common的值")
    @GetMapping("/common")
    public Common findCommon() {
        return commonService.findCommon();
    }

    @ApiOperation(value="验证日记的密码",notes = "验证日记密码")
    @PostMapping("/diary/check")
    public Map<String,String> checkDiaryPasd(@RequestBody String content) {
        Map<String,String> map = new HashMap<>();
        JSONObject object = JSON.parseObject(content);
        String password = object.getString("password");
        Common common = commonService.checkDiaryPASD(password);
        String token= JWTUtils.getToken(map);
        if (common!=null) {
            map.put("token",token);
            map.put("status", "success");
            map.put("message", "密码正确");
        }else {
            map.put("status", "fail");
            map.put("message", "密码错误");
        }
        return map;
    }

    @PostMapping("/diary/checktoken")
    @ApiOperation(value = "验证日记token",notes ="验证日记token")
    public Map<String,String> checkToken(@RequestBody String JsonObj) {
        Map<String,String> map = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(JsonObj);
        String token = jsonObject.getString("token");
        try {
            JWTUtils.verify(token);
            map.put("status","success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status","fail");
        }
        return map;
    }
}
