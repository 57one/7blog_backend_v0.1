package com.wu.blog.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.blog.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTinterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method=request.getMethod();
        if(method.equals("GET")||method.equals("OPTIONS")){
            return true;
        }
        Map<String,String> map=new HashMap<>();
        //获取请求头中的令牌
        String token=request.getHeader("token");
        try {
            JWTUtils.verify(token); //验证令牌
            return true; //放行
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("message","无效签名");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("message","token过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("message","token算法不一致");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","token无效");
        }
        map.put("status","fail"); //设置状态
        //将map转为json
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}

