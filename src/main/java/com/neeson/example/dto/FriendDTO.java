/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FriendDTO
 * Author:   ChiMon
 * Date:     2018/5/9 11:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.neeson.example.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ChiMon
 * @create 2018/5/9
 * @since 1.0.0
 */

@Entity
@Data
public class FriendDTO {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer friendId;
}
