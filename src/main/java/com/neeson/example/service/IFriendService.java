/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: IFriendService
 * Author:   ChiMon
 * Date:     2018/5/9 11:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.neeson.example.service;

import com.neeson.example.entity.FriendDto;
import com.neeson.example.entity.TempFriendDto;
import com.neeson.example.entity.UserDto;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ChiMon
 * @create 2018/5/9
 * @since 1.0.0
 */
public interface IFriendService {

    List<UserDto> getFriendList(Integer userId);

    TempFriendDto addFriend(Integer userId, Integer friendId);

    FriendDto deleteFriend(Integer userId, Integer friendId);

    FriendDto reAddFriend(Integer userId, Integer friendId,Integer state);
}
