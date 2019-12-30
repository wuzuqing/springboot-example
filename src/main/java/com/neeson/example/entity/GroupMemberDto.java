package com.neeson.example.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString(exclude = {"user","group"})
//@JSONType(ignores ={"user", "group"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_member")
public class GroupMemberDto  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean isTop;
    private Boolean isDisturb;
    private Boolean showGroupNickName;

    @Transient
    private String groupName;
    @Transient
    private Integer uId;
    @Transient
    private Integer gId;
    @Transient
    private String nick;
    @Transient
    private String avatar;
    @Transient
    private Integer groupManagerId;
    @Transient
    private String sex;

    //映射单向n-1的关联关系
    //使用@ManyToOne 来映射多对一的关系
    //使用@JoinColumn 来映射外键
    //可以使用@ManyToOne的fetch属性来修改默认的关联属性的加载策略
    @JsonIgnore
    @JoinColumn(name="user_id")//外键列的列名
    @ManyToOne
    private UserDto user;

    @JoinColumn(name="group1_id")//外键列的列名
    @ManyToOne
    private GroupDto group;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Boolean getDisturb() {
        return isDisturb;
    }

    public void setDisturb(Boolean disturb) {
        isDisturb = disturb;
    }

    public Boolean getShowGroupNickName() {
        return showGroupNickName;
    }

    public void setShowGroupNickName(Boolean showGroupNickName) {
        this.showGroupNickName = showGroupNickName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
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

    public Integer getGroupManagerId() {
        return groupManagerId;
    }

    public void setGroupManagerId(Integer groupManagerId) {
        this.groupManagerId = groupManagerId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    @JsonBackReference
    public UserDto getUser() {
        return user;
    }
    @JsonBackReference
    public void setUser(UserDto user) {
        this.user = user;
    }

    @JsonBackReference
    public GroupDto getGroup() {
        return group;
    }
    @JsonBackReference
    public void setGroup(GroupDto group) {
        this.group = group;
    }
}
