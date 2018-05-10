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

import com.neeson.example.dto.UserDTO;
import com.neeson.example.im.IMService;
import com.neeson.example.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    @Autowired
    UserRepository mUserRepository;

    @Override
    public List<UserDTO> getFriendList(String id) {
        List<UserDTO>  list  =new ArrayList<>();
        UserDTO friendDTO;
        for (int i = 0; i < 5; i++) {
            friendDTO  = new UserDTO();
            friendDTO.setId( i);
            friendDTO.setUserImg("http://p3.ifengimg.com/cmpp/2016/07/10/19/97ec5126-3731-4a75-909d-dccdf858f36f_size21_w363_h488.jpg");
            friendDTO.setUserName("大牛"+i);
            list.add(friendDTO);
        }

        return list;
    }

    @Override
    public UserDTO getUser(Integer id) {
        System.out.println("id:"+id);
        Optional<UserDTO> userDTO = mUserRepository.findById(id);
        if (userDTO.isPresent()) return userDTO.get();
        return new UserDTO();
    }
}
