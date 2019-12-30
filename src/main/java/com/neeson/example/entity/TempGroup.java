package com.neeson.example.entity;

import java.util.List;

public class TempGroup {
    private Integer id;
    private String groupName;//群组名称;
    private String avatar;//群组头像;
    private Integer online;//在线人数;
    private List<TempUserInfo> users;//组用户;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public List<TempUserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<TempUserInfo> users) {
        this.users = users;
    }

    public TempGroup(Integer id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public TempGroup() {
    }

    @Override
    public String toString() {
        return "TempGroup{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", online=" + online +
                ", users=" + users +
                '}';
    }
}
