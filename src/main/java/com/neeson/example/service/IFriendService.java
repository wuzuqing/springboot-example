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

import com.neeson.example.util.response.ResponseResult;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ChiMon
 * @create 2018/5/9
 * @since 1.0.0
 */
public interface IFriendService {

    ResponseResult getFriendList(Integer userId);

    ResponseResult addFriend(Integer userId, Integer friendId);

    ResponseResult deleteFriend(Integer userId, Integer friendId);

    ResponseResult reAddFriend(Integer userId, Integer friendId,Integer state);
}
