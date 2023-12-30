package com.wu.blog.service;

import com.wu.blog.dao.UserDao;
import com.wu.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public User findUserByName(String userName) {
        User user=userDao.findUserByName(userName);
        if(user!=null){
            return user;
        }
        throw new RuntimeException("无此用户名");
    }

    @Override
    @Transactional
    public User findUserByNameAndPassword(String userName, String password) {
        User user=userDao.findUserByNameAndPassword(userName,password);
        System.out.println(user);
        if(user!=null){
            return user;
        }
        throw new RuntimeException("登录失败");
    }

    @Override
    @Transactional
    public Integer addUser(User user) {
        User u = userDao.findUserByName(user.getUserName());
        if(u==null) {
            Date date = new Date();
            user.setCreateTime(date);
            user.setUpdateTime(date);
            return userDao.addUser(user);
        }else {
            throw new RuntimeException("已经存在相同的用户");
        }
    }
}
