package com.wu.blog.pojo;

import java.util.Date;
import java.util.List;

public class Comment {
    private Integer commentId; //评论id
    private Integer userId; //评论者id
    private Blog blog; //博客
    private String avatar; //评论头像
    private String fromName; //评论名称
    private Integer likes; //喜欢数
    private String content; //评论内容
    private String toName; //评论对象姓名
    private Date createTime; //评论时间
    private Integer parentId; //父评论id
    private List<Comment> children; //子评论
}
