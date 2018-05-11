/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: IFriendServiceImpl
 * Author:   ChiMon
 * Date:     2018/5/9 11:44
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.neeson.example.service.impl;

import com.neeson.example.dto.UserDTO;
import com.neeson.example.repository.UserRepository;
import com.neeson.example.service.IUserService;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
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
    public UserDTO getUser(Integer id) {
        System.out.println("id:" + id);
        Optional<UserDTO> userDTO = mUserRepository.findById(id);
        if (userDTO.isPresent()) return userDTO.get();
        return new UserDTO();
    }

    @Override
    public UserDTO register(String phone, String pwd) {
        if (mUserRepository.findByPhone(phone)!=null){
            return null;
        }
        UserDTO user = new UserDTO();
        user.setPhone(phone);
        user.setPwd(pwd);
        mUserRepository.save(user);
        UserModel userModel = new UserModel()
                .setId(String.valueOf(user.getId()))
                .setName(user.getPhone())
                .setPortrait("http://www.rongcloud.cn/images/logo.png");
        try {
            TokenResult result = RongCloud.getInstance().user.register(userModel);
            user.setRongToken(result.getToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mUserRepository.save(user);
        return user;
    }

    @Override
    public UserDTO login(String phone, String pwd) {
        UserDTO userDTO = mUserRepository.findByPhoneAndPwd(phone, pwd);
        return userDTO;
    }

    @Override
    public String refreshRondToken(Integer id) {
        UserDTO user = getUser(id);
        if (user!=null){
            UserModel userModel = new UserModel()
                    .setId(String.valueOf(user.getId()))
                    .setName(user.getPhone())
                    .setPortrait(user.getUserImg());
            try {
                TokenResult result = RongCloud.getInstance().user.register(userModel);
                user.setRongToken(result.getToken());
                mUserRepository.save(user);
                return result.getToken();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
