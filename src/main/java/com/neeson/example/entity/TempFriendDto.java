package com.neeson.example.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "temp_friend")
public class TempFriendDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;  //发起添加好友的id
    private Integer friendId;//被添加好友的id
    private Integer groupId;
    private Integer state;      //状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
