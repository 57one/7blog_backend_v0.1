package com.wu.blog.controller;

import com.wu.blog.pojo.Blog;
import com.wu.blog.pojo.Common;
import com.wu.blog.pojo.Type;
import com.wu.blog.service.BlogService;
import com.wu.blog.service.CommonService;
import com.wu.blog.service.TypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    CommonService commonService;
    @Autowired
    TypeService typeService;

    @ApiOperation(value = "分页查询博客",notes = "分页查询博客")
    @GetMapping("/blogs/{pageSize}/{size}")
    public List<Blog> findBlogsOfPages(@PathVariable("pageSize") Integer pageSize,@PathVariable("size")Integer size) {
        Integer begin=Integer.valueOf((pageSize-1)*size);
        return blogService.findBlogsOfPages(begin,size);
    }

    @ApiOperation(value = "查询博客总数",notes="查询博客总数")
    @GetMapping("/blogs/nums")
    public Integer findBlogsNum() {
        return blogService.findBlogsNum();
    }

    @ApiOperation(value = "根据id删除博客",notes = "根据id删除博客")
    @DeleteMapping("/blog/{blogId}")
    public void deleteBlogById(@PathVariable("blogId") Integer blogId) {
        Blog blog = blogService.findBlogById(blogId);
//        if(blog.getPublished()) {
//            这里需要增加一个接口 来查询已经发布的博客数量
//        }
        Common c = commonService.findCommon();
        Common common=new Common();
        common.setBlogs(c.getBlogs()-1);
        commonService.updataCommon(common);
        //类型数量减一
        Integer typeId = blogService.findBlogById(blogId).getType().getTypeId();
        Type type = typeService.findTypeById(typeId);
        while (type!=null) {
            Integer typeSize = type.getTypeSize()-1;
            type.setTypeSize(typeSize);
            typeService.updateTypeSize(type);
            typeId=type.getParentId();
            type=typeService.findTypeById(typeId);
        }
        blogService.deleteBlogById(blogId);
    }

    @ApiOperation(value = "增加博客",notes = "增加博客")
    @PostMapping("/blog")
    public Map<String,String> addBlog(@RequestBody Blog blog) {
        Map<String,String> map=new HashMap<>();
        Blog b = blogService.findBlogByTitle(blog.getTitle());
        if(b!=null) {
            map.put("status","fail");
            map.put("message","已经存在同样标题的博客");
        } else {
            Integer i = blogService.addBlog(blog);
            if(i==null) {
                map.put("status","fail");
                map.put("message","新增博客失败");
            }else {
                map.put("status","success");
                map.put("message","新增博客成功");
//                if(blog.getPublished()) {
//                  这里也是相同的逻辑
//                }
                Common c = commonService.findCommon();
                Common common=new Common();
                common.setBlogs(c.getBlogs()+1);
                commonService.updataCommon(common);
                //类型数量加一
                Integer typeId = blog.getType().getTypeId();
                Type type = typeService.findTypeById(typeId);
                while (type!=null) {
                    Integer typeSize = type.getTypeSize()+1;
                    type.setTypeSize(typeSize);
                    typeService.updateTypeSize(type);
                    typeId=type.getParentId();
                    type=typeService.findTypeById(typeId);
                }
            }
        }
        return map;
    }

    @ApiOperation(value = "根据id查询博客",notes = "根据id查询博客")
    @GetMapping("/blog/{blogId}")
    public Blog findBlogById(@PathVariable("blogId") Integer blogId) {
        return blogService.findBlogById(blogId);
    }

    @ApiOperation(value="更新博客",notes = "更新博客")
    @PutMapping("/blog")
    public Map<String,String> updateBlog(@RequestBody Blog blog) {
        Map<String,String> map = new HashMap<>();
        Blog b=blogService.findBlogByTitle(blog.getTitle());
        if(blog.getTitle().equals(b.getTitle())&&blog.getBlogId()!=b.getBlogId()) {
            map.put("status","fail");
            map.put("message","已经存在同样标题的博客");
        } else {
            Integer i = blogService.updateBlog(blog);
            if(i==null) {
                map.put("status","fail");
                map.put("message","更新博客失败");
            }else {
                map.put("status","success");
                map.put("message","更新博客成功");
            }
        }
        return map;
    }

    @ApiOperation(value="根据条件查询博客",notes = "Tomcat从8以上的某个版本 get参数过多会有问题")
    @PostMapping("/blog/search")
    public List<Blog> findBlogsByCondition(@RequestBody Blog blog) {
        String title="%"+blog.getTitle()+"%";
        blog.setTitle(title);
        return blogService.findBlogsByCondition(blog);
    }

    @ApiOperation(value ="根据条件进行分页查询博客",notes = "Tomcat从8以上的某个版本 get参数过多会有问题")
    @PostMapping("/blog/search/{pageSize}/{size}")
    public List<Blog> findBlogsByConditionOfPages(@RequestBody Blog blog,@PathVariable("pageSize") Integer pageSize,@PathVariable("size") Integer size) {
        Integer begin = Integer.valueOf((pageSize-1)*size);
        return blogService.findBlogsByConditionOfPages(blog,begin,size);
    }

    @ApiOperation(value = "根据id查询博客并转换",notes = "转换markdown为html")
    @GetMapping("/blogs/{blogId}")
    public Blog findBlogAndConvertById(@PathVariable("blogId")Integer blogId) {
        return blogService.findBlogAndConvertById(blogId);
    }

    @ApiOperation(value = "分页查询已经发布的博客",notes = "分页查询已经发布的博客")
    @GetMapping("/blogs/published/{pageSize}/{size}")
    public List<Blog> findBlogsIsPublished(@PathVariable("pageSize") Integer pageSize,@PathVariable("size")Integer size) {
        Integer begin = Integer.valueOf((pageSize-1)*size);
        return blogService.findBlogsIsPublished(begin,size);
    }

    @ApiOperation(value = "查询已经发布博客的总数",notes = "查询已经发布博客的总数")
    @GetMapping("/blogs/published")
    public Integer findBlogsNumIsPublished(){
        return blogService.findBlogsNumIsPublished();
    }

    @ApiOperation(value = "根据类型分页查询已经发布博客",notes = "根据类型分页查询博客")
    @GetMapping("/blogs/{typeId}/{pageSize}/{size}")
    public List<Blog> findBlogsByTypeId(@PathVariable("typeId")Integer typeId,@PathVariable("pageSize")Integer pageSize,@PathVariable("size")Integer size) {
        Integer begin = Integer.valueOf((pageSize-1)*size);
        return blogService.findBlogsByTypeId(typeId,begin,size);
    }

    @ApiOperation(value="根据类型查询已经发布博客的数目",notes = "根据类型查询数目")
    @GetMapping("/blogs/nums/{typeId}")
    public Integer findBlogsNumByTypeId(@PathVariable("typeId")Integer typeId) {
        return blogService.findBlogsNumByTypeId(typeId);
    }

    @ApiOperation(value = "分页查询已经发布博客中推荐的博客",notes = "分页查询已经发布博客中推荐的博客")
    @GetMapping("/blogs/recommend/{pageSize}/{size}")
    public List<Blog> findBlogsRecommend(@PathVariable("pageSize")Integer pageSize,@PathVariable("size")Integer size){
        Integer begin = Integer.valueOf((pageSize-1)*size);
        return blogService.findBlogsRecommend(begin,size);
    }

    @ApiOperation(value = "查询已经发布博客中推荐博客的数目",notes = "查询已经发布博客中推荐博客的数目")
    @GetMapping("/blogs/recommend")
    public Integer findBlogsNumRecommend(){
        return blogService.findBlogsNumRecommend();
    }
}