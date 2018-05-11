/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserDTO
 * Author:   ChiMon
 * Date:     2018/5/10 17:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.neeson.example.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ChiMon
 * @create 2018/5/10
 * @since 1.0.0
 */
@Entity
@Data
public class UserDTO {

    @Id
    @GeneratedValue
    private Integer id;


    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String pwd;

    private String userName; //昵称
    private String userImg; //头像
    private String address; //地址
    private String sex;     //性别
    private String sign;    //签名
    private String rongToken; //融云token
}
