package com.wu.blog.service;

import com.wu.blog.pojo.User;

public interface UserService {

    User findUserByName(String userName);

    User findUserByNameAndPassword(String userName,String password);

    Integer addUser(User user);
}
