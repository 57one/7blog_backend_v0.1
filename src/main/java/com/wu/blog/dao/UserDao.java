package com.wu.blog.dao;

import com.wu.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    //根据userName查询用户
    User findUserByName(String userName);

    //根据userName和password查询用户
    User findUserByNameAndPassword(@Param("userName")String userName, @Param("password")String password);

    Integer addUser(User user);
}
