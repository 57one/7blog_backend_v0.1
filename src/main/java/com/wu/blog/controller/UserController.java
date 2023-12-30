package com.wu.blog.controller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wu.blog.pojo.Common;
import com.wu.blog.pojo.User;
import com.wu.blog.service.CommonService;
import com.wu.blog.service.UserService;
import com.wu.blog.utils.JWTUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonService commonService;

    @PostMapping("/user/login")
    @ApiOperation(value = "登录验证",notes = "登录验证")
    public Map<String,String> login(@RequestBody User user){
        Map<String,String> map=new HashMap<>();
        try{
            User userDB=userService.findUserByNameAndPassword(user.getUserName(),user.getPassword());
            Integer typeId = userDB.getTypeId();
            Map<String,String> payLoad=new HashMap<>();
            payLoad.put("id",userDB.getUserId().toString());
            payLoad.put("avatar",userDB.getAvatar());
            payLoad.put("typeID",userDB.getTypeId().toString());
            payLoad.put("nickName",userDB.getNickName());
            if(typeId==1) {
                //生成jwt令牌
                String token= JWTUtils.getToken(payLoad);
                map.put("token",token);
            } else {
                String userInfo = JSON.toJSONString(payLoad);
                map.put("userInfo",userInfo);
            }
            map.put("status","success");
            map.put("message","登录成功");
        }catch (Exception e) {
            map.put("status","fail");
            map.put("message","登录失败");
        }
        return map;
    }

    @PostMapping("user/check")
    @ApiOperation(value = "登录校验权力",notes = "登录校验权力")
    public Map<String,String> loginCheck(@RequestBody String token) {
        Map<String,String> map=new HashMap<>();
        try {
            JWTUtils.verify(token);
            DecodedJWT decodeToken = JWTUtils.getToken(token);
            String payload = decodeToken.getPayload();
            map.put("status","success");
            map.put("message","登录正确");
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("status","fail");
            map.put("message","无效签名");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("status","fail");
            map.put("message","token过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("status","fail");
            map.put("message","token算法不一致");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status","fail");
            map.put("message","token失效");
        }
        return map;
    }

    @PostMapping("user/check/getDiaryToken")
    @ApiOperation(value = "通过token获取dairyToken",notes = "通过token获取diaryToken")
    public Map<String,String> getDiaryTokenByToken(@RequestBody String token) {
        Map<String,String> map=new HashMap<>();
        try {
            JWTUtils.verify(token);
            Common common= commonService.findCommon();
            String diaryToken = JWTUtils.getToken(map);
            map.put("token",token);
            map.put("status","success");
            map.put("message","登录正确");
        } catch (Exception e) {
            map.put("status","fail");
            map.put("message",e.getMessage());
        }
        return map;
    }

    @PostMapping("user/add")
    @ApiOperation(value = "添加用户",notes = "添加用户")
    public Map<String,String> addUser(@RequestBody User user) {
        Map<String,String> map=new HashMap<>();
        try{
            Integer i=userService.addUser(user);
            User userDB = userService.findUserByNameAndPassword(user.getUserName(),user.getPassword());
            Map<String,String> payLoad=new HashMap<>();
            payLoad.put("id",userDB.getUserId().toString());
            payLoad.put("avatar",userDB.getAvatar());
            payLoad.put("typeID",userDB.getTypeId().toString());
            payLoad.put("nickName",userDB.getNickName());
            //生成jwt令牌
            String token= JWTUtils.getToken(payLoad);
            map.put("status","success");
            map.put("message","注册成功");
            map.put("token",token);
        }catch (Exception e) {
            map.put("status","fail");
            map.put("message",e.getMessage());
        }
        return map;
    }
}
