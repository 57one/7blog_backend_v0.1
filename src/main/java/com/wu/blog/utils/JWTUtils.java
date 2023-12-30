package com.wu.blog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {

    private static String TOKEN_SECERT="57one@Token!Q@W3Es";

    //生成token 传入payload
    public static String getToken(Map<String,String> map) {
        JWTCreator.Builder builder= JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,60);
        builder.withExpiresAt(instance.getTime()); //指定令牌过期时间
        String token =builder.sign(Algorithm.HMAC256(TOKEN_SECERT)).toString();
        return token;
    }

    //验证token 合法性
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN_SECERT)).build().verify(token);
    }

    //获取token中的payload
    public static DecodedJWT getToken(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN_SECERT)).build().verify(token);
    }
}
