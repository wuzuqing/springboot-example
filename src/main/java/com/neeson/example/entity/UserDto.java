package com.neeson.example.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
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



    //使用@JoinColumn 来映射外键列的名称
    //使用@OneToMany 来映射一对多的关联关系
    //可以使用@OneToMany的fetch 属性来修改默认的加载策略
    //可以通过@OneToMany的cascade 属性来修改默认的删除策略
    //cascade={CascadeType.REMOVE} 会把主表和从表的数据都删除
    //mappedBy表明放弃关联关系维护并且不能在使用
    //注意：若在一的一端@oneToMany 中使用mapperBy属性，则@oneToMany端就不能在使用@JoinColumn(name="CUSTOMER_ID")属性
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy = "user")
    private List<GroupMemberDto> groupMemberList ;
}
