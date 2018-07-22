package com.neeson.example.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "group_member")
public class GroupMemberDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer groupId;
    private Boolean isTop;
    private Boolean isDisturb;
    private Boolean showGroupNickName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
}
