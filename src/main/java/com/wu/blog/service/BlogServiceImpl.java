package com.wu.blog.service;

import com.wu.blog.dao.BlogDao;
import com.wu.blog.dao.TypeDao;
import com.wu.blog.pojo.Blog;
import com.wu.blog.pojo.Type;
import com.wu.blog.utils.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;
    @Autowired
    TypeDao typeDao;

    @Override
    @Transactional
    public List<Blog> findBlogsOfPages(Integer begin,Integer size) {
        return blogDao.findBlogsOfPages(begin,size);
    }

    @Override
    @Transactional
    public Integer findBlogsNum() {
        return blogDao.findBlogsNum();
    }

    @Override
    @Transactional
    public void deleteBlogById(Integer blogId) {
        blogDao.deleteBlogById(blogId);
    }

    @Override
    @Transactional
    public Integer addBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        return blogDao.addBlog(blog);
    }

    @Override
    @Transactional
    public Blog findBlogByTitle(String title) {
        return blogDao.findBlogByTitle(title);
    }

    @Override
    @Transactional
    public Blog findBlogById(Integer blogId) {
        return blogDao.findBlogById(blogId);
    }

    @Override
    @Transactional
    public Integer updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        return blogDao.updateBlog(blog);
    }

    @Override
    @Transactional
    public List<Blog> findBlogsByCondition(Blog blog) {
        return blogDao.findBlogsByCondition(blog);
    }

    @Override
    @Transactional
    public List<Blog> findBlogsByConditionOfPages(Blog blog,Integer begin,Integer size) {
        return blogDao.findBlogsByConditionOfPages(blog,begin,size);
    }

    @Override
    @Transactional
    public Blog findBlogAndConvertById(Integer blogId) {
        Blog blog = blogDao.findBlogById(blogId);
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        return blog;
    }

    @Override
    public List<Blog> findBlogsByTypeId(Integer typeId, Integer begin, Integer size) {
        List<Integer> typeIds;
        Type type=typeDao.findTypeById(typeId);
        if(type.getParentId()==0){
            typeIds=typeDao.findTypeIdByParentId(typeId);
            typeIds.add(typeId);
        }else {
            typeIds=new ArrayList<Integer>();
            typeIds.add(typeId);
        }
        return blogDao.findBlogsByTypeId(typeIds,begin,size);
    }

    @Override
    public Integer findBlogsNumByTypeId(Integer typeId) {
        List<Integer> typeIds;
        Type type = typeDao.findTypeById(typeId);
        if(type.getParentId()==0) {
            typeIds=typeDao.findTypeIdByParentId(typeId);
        }else {
            typeIds=new ArrayList<Integer>();
            typeIds.add(typeId);
        }
        return blogDao.findBlogsNumByTypeId(typeIds);
    }

    @Override
    public List<Blog> findBlogsRecommend(Integer begin, Integer size) {
        Blog blog=new Blog();
        blog.setPublished(true);
        blog.setRecommend(true);
        return blogDao.findBlogsByConditionOfPages(blog,begin,size);
    }

    @Override
    public Integer findBlogsNumRecommend() {
        Blog blog=new Blog();
        blog.setPublished(true);
        blog.setRecommend(true);
        return blogDao.findBlogsNumByConditionOfPages(blog);
    }

    @Override
    public List<Blog> findBlogsIsPublished(Integer begin, Integer size) {
        Blog blog=new Blog();
        blog.setPublished(true);
        return blogDao.findBlogsByConditionOfPages(blog,begin,size);
    }

    @Override
    public Integer findBlogsNumIsPublished() {
        Blog blog=new Blog();
        blog.setPublished(true);
        return blogDao.findBlogsNumByConditionOfPages(blog);
    }
}
