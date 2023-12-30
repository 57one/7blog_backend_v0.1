package com.wu.blog.dao;

import com.wu.blog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {

    /*
    * 找出所有类型
    * 类型格式
    * - 父类
    *   - 子类
    * */
    List<Type> findAllTypes();

    //找出所有父类
    List<Type> findAllParentTypes();

    //添加分类
    Integer addType(Type type);

    //根据typeId删除类型
    Integer deleteTypeById(Integer typeId);

    //更新Type
    Integer updateType(Type type);

    //根据typeId寻找Type
    Type findTypeById(Integer typeId);

    //分页查询
    List<Type> findTypesOfPages(@Param("begin")Integer begin,@Param("size") Integer size);

    //根据typeName查询
    List<Type> findTypesByTypeName(String typeName);

    //根据父id查询子id
    List<Integer> findTypeIdByParentId(Integer parentId);

    void updateTypeSize(Type type);
}
