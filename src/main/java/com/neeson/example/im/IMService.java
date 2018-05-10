/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: IMService
 * Author:   ChiMon
 * Date:     2018/5/9 11:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.neeson.example.im;

import com.neeson.example.dto.UserDTO;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ChiMon
 * @create 2018/5/9
 * @since 1.0.0
 */
public interface IMService {

    List<UserDTO> getFriendList(String id);

    UserDTO getUser(Integer id);
}
