package com.wu.blog.dao;

import com.wu.blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogDao {

    //分页查找博客
    List<Blog> findBlogsOfPages(@Param("begin")Integer begin, @Param("size") Integer size);

    //查询博客数目
    Integer findBlogsNum();

    //根据id删除博客
    void deleteBlogById(Integer blogId);

    //增加博客
    Integer addBlog(Blog blog);

    //根据name查询博客
    Blog findBlogByTitle(String title);

    //根据Id查询博客
    Blog findBlogById(Integer blogId);

    //更新博客
    Integer updateBlog(Blog blog);

    //根据条件查询博客
    List<Blog> findBlogsByCondition(@Param("blog")Blog blog);

    //根据条件查询博客并且分页
    List<Blog> findBlogsByConditionOfPages(@Param("blog")Blog blog,@Param("begin")Integer begin,@Param("size")Integer size);

    //根据条件查询博客的总数
    Integer findBlogsNumByConditionOfPages(@Param("blog")Blog blog);

    //根据类型id查询分页查询已经发布博客
    List<Blog> findBlogsByTypeId(@Param("typeIds")List<Integer> typeIds,@Param("begin")Integer begin,@Param("size")Integer size);

    //根据类型id查询已经发布博客数目
    Integer findBlogsNumByTypeId(@Param("typeIds")List<Integer> typeIds);
}
