/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: IMServiceImpl
 * Author:   ChiMon
 * Date:     2018/5/9 11:44
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.neeson.example.im.impl;

import com.neeson.example.dto.FriendDTO;
import com.neeson.example.im.IMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ChiMon
 * @create 2018/5/9
 * @since 1.0.0
 */

@Service
@Slf4j
public class IMServiceImpl implements IMService {


    @Override
    public List<FriendDTO> getFriendList(String id) {
        List<FriendDTO>  list  =new ArrayList<>();
        FriendDTO friendDTO;
        for (int i = 0; i < 5; i++) {
            friendDTO  = new FriendDTO();
            friendDTO.setUserId(i);
            friendDTO.setUserImg("http://p3.ifengimg.com/cmpp/2016/07/10/19/97ec5126-3731-4a75-909d-dccdf858f36f_size21_w363_h488.jpg");
            friendDTO.setUserName("大牛"+i);
            list.add(friendDTO);
        }

        return list;
    }
}
