package com.neeson.example.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@ToString
@Data
@Table(name = "group1")
public class GroupDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String groupName;
    private Long createTime; //创建时间
    private String groupNotice;//群公告
    private Integer groupManagerId;

    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy = "group")
    private List<GroupMemberDto> groupMemberList ;

}
