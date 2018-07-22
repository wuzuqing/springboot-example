package com.neeson.example.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "friend")
public class FriendDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer friendId;
    private Integer state;
    private Integer deleteSum;

}
