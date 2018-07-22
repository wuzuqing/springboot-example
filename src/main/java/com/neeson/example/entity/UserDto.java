package com.neeson.example.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String account ;        //用户名
    private String password;    //密码
    private String status;      //登陆状态
    private String token;       //token
    private String terminal;      //  用户所属终端 登陆设备
    private String nick;   //昵称
    private String avatar;      //头像
    private String des;    //描述
    private String sign;        //签名
    private String phone;       //手机号
    private String address;     //地址
    private String sex;         //性别
    private String birthday;    //生日
    private Long registerTime;  //注册时间
    private Long lastLoginTime; //最后一次登陆时间
    private String extra1;      //附加参数1
    private String extra2;      //附加参数2

}
