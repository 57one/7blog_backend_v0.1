package com.wu.blog.pojo;

import java.util.Date;

public class User {
    private Integer userId; //用户id
    private String avatar; //用户头像地址
    private String userName; //用户名
    private String nickName; //昵称
    private String password; //密码
    private String email; //邮箱
    private Integer typeId; //用户类型 1代表管理员 0代表普通用户
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

    public User(Integer userId, String avatar, String userName, String nickName, String password, String email, Integer typeId, Date createTime, Date updateTime) {
        this.userId = userId;
        this.avatar = avatar;
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.typeId = typeId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
