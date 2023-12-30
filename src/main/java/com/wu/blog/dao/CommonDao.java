package com.wu.blog.dao;

import com.wu.blog.pojo.Common;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommonDao {
    //查找Common信息
    Common findCommon();

    //验证日记密码
    Common checkDiaryPASD(String password);

    void updateCommon(Common common);
}
