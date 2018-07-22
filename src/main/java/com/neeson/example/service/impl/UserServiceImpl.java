/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FriendServiceImpl
 * Author:   ChiMon
 * Date:     2018/5/9 11:44
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.neeson.example.service.impl;

import com.neeson.example.entity.UserDto;
import com.neeson.example.repository.UserRepository;
import com.neeson.example.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl implements IUserService {


    @Autowired
    UserRepository mUserRepository;

    @Override
    public UserDto getUser(Integer id) {
        Optional<UserDto> User = mUserRepository.findById(id);
        if (User.isPresent()) return User.get();
        return new UserDto();
    }

    @Override
    public UserDto searchUser(String phone) {
        return mUserRepository.findByAccountOrPhone(phone,phone);
    }
    private String avatar="https://timgsa.baidu" +
            ".com/timg?image&quality=80&size=b9999_10000&sec=1532196265038&di=ebeb0b2f52342d1933738a12adaf0adb&imgtype=0&src=http%3A%2F%2Fimg10.360buyimg.com%2Fimgzone%2Fjfs%2Ft2077%2F249%2F2855394388%2F434290%2Fdae5739d%2F56f4af8eN29dfc2e5.jpg";
    @Override
    public UserDto register(String phone, String pwd) {
        if (mUserRepository.findByAccount(phone)!=null){
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setAccount(phone);
        userDto.setPassword(pwd);
        userDto.setAvatar(avatar);
        userDto.setSex("男");
        mUserRepository.save(userDto);
        return userDto;
    }

    @Override
    public UserDto login(String phone, String pwd) {
        return  mUserRepository.findByAccountOrPhone(phone,phone);
    }

    @Override
    public String refreshRondToken(Integer id) {

        return null;
    }
}
