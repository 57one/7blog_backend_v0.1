package com.wu.blog.service;

import com.wu.blog.pojo.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> findBlogsOfPages(Integer begin,Integer size);

    Integer findBlogsNum();

    void deleteBlogById(Integer blogId);

    Integer addBlog(Blog blog);

    Blog findBlogByTitle(String title);

    Blog findBlogById(Integer blogId);

    Integer updateBlog(Blog blog);

    List<Blog> findBlogsByCondition(Blog blog);

    List<Blog> findBlogsByConditionOfPages(Blog blog,Integer begin,Integer size);

    Blog findBlogAndConvertById(Integer blogId);

    List<Blog> findBlogsByTypeId(Integer typeId,Integer begin,Integer size);

    Integer findBlogsNumByTypeId(Integer typeId);

    List<Blog> findBlogsRecommend(Integer begin,Integer size);

    Integer findBlogsNumRecommend();

    List<Blog> findBlogsIsPublished(Integer begin,Integer size);

    Integer findBlogsNumIsPublished();
}
