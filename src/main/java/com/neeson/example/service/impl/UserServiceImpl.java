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

import com.neeson.example.entity.*;
import com.neeson.example.repository.UserRepository;
import com.neeson.example.service.IUserService;
import com.neeson.example.util.RandomValue;
import com.neeson.example.util.RedisUtil;
import com.neeson.example.util.response.ResponseResult;
import com.neeson.example.util.response.RestResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.neeson.example.util.ResultUtil.clearGm;

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


    @Autowired
    GroupServiceImpl groupService;

    @Autowired
    FriendServiceImpl friendService;


    @Resource
    private RedisUtil redisUtil;

    @Override
    public ResponseResult getUserInfoByToken(String token) {
        UserDto user = mUserRepository.findByToken(token);
        if (user == null) {
            return RestResultGenerator.genResult(null, "获取失败,找不到该用户");
        }
        TempUserInfo tempUserInfo = change(user);

        ResponseResult<List<GroupDto>> groupList = groupService.getGroupList(user.getId());
        List<GroupDto> groupDtos = groupList.getData();
        if (groupDtos != null) {
            List<TempGroup> tempGroups = new ArrayList<>();
            List<TempUserInfo> tempUserInfos;
            TempGroup group;
            StringBuffer sb = new StringBuffer();
            int count;
            for (GroupDto dto : groupDtos) {
                group = new TempGroup();
                group.setId(dto.getId());
                group.setGroupName(dto.getGroupName());
                sb.setLength(0);
                List<GroupMemberDto> list = dto.getGroupMemberList();
                tempUserInfos = new ArrayList<>();
                count = 0;
                for (GroupMemberDto memberDto : list) {
                    TempUserInfo info = change(memberDto.getUser());
                    tempUserInfos.add(info);
                    if (count < 9) {
                        sb.append(",").append(info.getAvatar());
                        count++;
                    }
                }
                if (count > 0) {
                    sb.deleteCharAt(0);
                    group.setAvatar(sb.toString());
                }
                group.setUsers(tempUserInfos);
                tempGroups.add(group);
            }
            tempUserInfo.setGroups(tempGroups);
        }
        ResponseResult<List<UserDto>> friendList = friendService.getFriendList(user.getId());
        List<TempGroup> friends = new ArrayList<>();
        TempGroup myFriend = new TempGroup(1, "我的好友");
        if (friendList != null) {
            List<UserDto> data = friendList.getData();
            List<TempUserInfo> tempUserInfos = new ArrayList<>();
            for (UserDto datum : data) {
                tempUserInfos.add(change(datum));
            }
            myFriend.setUsers(tempUserInfos);
        }
        friends.add(myFriend);
        tempUserInfo.setFriends(friends);
        return RestResultGenerator.genResult(tempUserInfo, "获取成功");
    }

    private TempUserInfo change(UserDto user) {
        TempUserInfo tempUserInfo = new TempUserInfo();
        tempUserInfo.setAvatar(user.getAvatar());
        tempUserInfo.setId(user.getId());
        tempUserInfo.setNick(user.getNick());
        tempUserInfo.setSign(user.getSign());
        tempUserInfo.setTerminal(user.getTerminal());
        tempUserInfo.setStatus(user.getStatus());
        tempUserInfo.setToken(user.getToken());
        return tempUserInfo;
    }

    @Override
    public ResponseResult getUser(Integer id) {

        UserDto result = null;
        String key = "user_" + id;
        result = redisUtil.getObject(key, UserDto.class);
        if (result == null) {
            Optional<UserDto> userDto = mUserRepository.findById(id);
            if (userDto.isPresent()) {
                result = userDto.get();
                redisUtil.setObject(key, result);
            }
        }
        if (result != null) {
            clearGm(result);
            return RestResultGenerator.genResult(result, "获取成功");
        } else {
            return RestResultGenerator.genResult(null, "获取失败,找不到该用户");
        }
    }

    @Override
    public ResponseResult searchUser(String phone) {
        UserDto userDto = mUserRepository.findByAccountOrPhone(phone, phone);
        clearGm(userDto);
        if (userDto == null) {
            return RestResultGenerator.genErrorResult("没有此用户,请重新输入");
        } else {
            return RestResultGenerator.genResult(userDto, "搜索成功");
        }
    }


    @Override
    public ResponseResult register(String phone, String pwd) {
        if (mUserRepository.findByAccount(phone) != null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setAccount(phone);
        userDto.setPassword(pwd);
        userDto.setAvatar(RandomValue.getAvatar());
        userDto.setNick(RandomValue.getChineseName());
        userDto.setSex(RandomValue.getSex());
        userDto.setToken(getToken());
        mUserRepository.save(userDto);
        if (userDto == null) {
            return RestResultGenerator.genErrorResult("该手机号已注册,请更换手机号码再试");
        } else {
            return RestResultGenerator.genResult(userDto, "注册成功");
        }
    }

    /**
     * 生成token
     *
     * @return
     */
    public static String getToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public ResponseResult login(String phone, String pwd) {
        UserDto userDto = mUserRepository.findByAccountOrPhone(phone, phone);
        if (userDto == null) {
            return RestResultGenerator.genErrorResult("登录失败,该手机号尚未注册");
        } else {
            if (userDto.getPassword().equals(pwd)) {
                userDto.setToken(getToken());
                mUserRepository.save(userDto);
                clearGm(userDto);
                return RestResultGenerator.genResult(userDto, "登录成功");
            } else {
                return RestResultGenerator.genErrorResult("密码错误");
            }
        }
    }

    @Override
    public ResponseResult testRedis(Integer code) {
        String key = "key" + code;
        redisUtil.set(key, code);
        return RestResultGenerator.genResult(redisUtil.hasKey(key), "success");
    }
}
