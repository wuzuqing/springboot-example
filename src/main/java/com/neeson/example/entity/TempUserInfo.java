package com.neeson.example.entity;

import java.util.List;

public class TempUserInfo {
    private Integer id;//用户id;
    private String nick; //user nick
    private String avatar; //用户头像
    private String status;//在线状态(online、offline)
    private String sign;//个性签名;
    private String terminal;//用户所属终端;
    private String token;
    private List<TempGroup> friends;//好友列表;
    private List<TempGroup> groups;//群组列表;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<TempGroup> getFriends() {
        return friends;
    }

    public void setFriends(List<TempGroup> friends) {
        this.friends = friends;
    }

    public List<TempGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<TempGroup> groups) {
        this.groups = groups;
    }
}
