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

import com.neeson.example.entity.FriendDto;
import com.neeson.example.entity.TempFriendDto;
import com.neeson.example.entity.UserDto;
import com.neeson.example.repository.FriendRepository;
import com.neeson.example.repository.TempFriendRepository;
import com.neeson.example.repository.UserRepository;
import com.neeson.example.service.IFriendService;
import com.neeson.example.util.response.ResponseResult;
import com.neeson.example.util.response.RestResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class FriendServiceImpl implements IFriendService {


    @Autowired
    private TempFriendRepository tempFriendRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseResult getFriendList(Integer id) {
        List<FriendDto> friendDtoDTOS1 = friendRepository.findByUserIdOrFriendId(id, id);
        if (friendDtoDTOS1 == null || friendDtoDTOS1.isEmpty()) {
            return null;
        }
        List<Integer> ids = new ArrayList<>();
        for (FriendDto friendDTO : friendDtoDTOS1) {
            //双方都删除对方了,则都不返回
            if (friendDTO.getUserId() == id) {
                if (friendDTO.getDeleteSum() != friendDTO.getFriendId()) {
                    ids.add(friendDTO.getFriendId());
                }
            } else {
                if (friendDTO.getDeleteSum() != friendDTO.getUserId()) {
                    ids.add(friendDTO.getUserId());
                }
            }
        }
        if (ids.isEmpty()) {
            return null;
        }
        List<UserDto> userDtos = userRepository.findById(ids);
        if (userDtos != null) {
            for (UserDto userDto : userDtos) {
                clearGm(userDto);
            }
        }
        if (userDtos == null) {
            return RestResultGenerator.genErrorResult("您目前好友列表为空");
        } else {
            return RestResultGenerator.genResult(userDtos, "");
        }
    }

    @Override
    public ResponseResult addFriend(Integer userId, Integer friendId) { //userId 小,friendId 大
        Optional<UserDto> userDto = userRepository.findById(friendId);
        if (userDto.get() == null) {
            return null;
        }
        TempFriendDto friendDtoDTO = tempFriendRepository.findByUserIdAndFriendId(userId, friendId);
        if (friendDtoDTO == null) {
            friendDtoDTO = new TempFriendDto();
            friendDtoDTO.setUserId(userId);
            friendDtoDTO.setFriendId(friendId);
            tempFriendRepository.save(friendDtoDTO);
        }
        if (friendDtoDTO == null) {
            return RestResultGenerator.genResult(null, "申请添加好友失败,该好友不是有效的用户");
        } else {
            return RestResultGenerator.genResult(friendDtoDTO, "申请添加好友成功");
        }
    }

    @Override
    public ResponseResult deleteFriend(Integer userId, Integer friendId) {

        int deleteId = friendId;
        int tempId;
        if (userId > friendId) {
            tempId = userId;
            userId = friendId;
            friendId = tempId;
        }
        FriendDto friendDto = friendRepository.findByUserIdAndFriendId(userId, friendId);
        if (friendDto.getDeleteSum() == null) {
            friendDto.setDeleteSum(deleteId);
            friendRepository.save(friendDto);
        } else {
            friendRepository.delete(friendDto);
        }
        if (friendDto == null) {
            return RestResultGenerator.genResult(null, "申请添加好友失败,该好友不是有效的用户");
        } else {
            return RestResultGenerator.genResult(friendDto, "申请添加好友成功");
        }
    }

    @Override
    public ResponseResult reAddFriend(Integer userId, Integer friendId, Integer state) {
        TempFriendDto tempFriendDto = tempFriendRepository.findByUserIdAndFriendId(userId, friendId);
        if (tempFriendDto == null) return RestResultGenerator.genResult(null, "对方拒绝添加好友");
        switch (state) {
            case 1: //同意
                FriendDto friendDto = new FriendDto();
                int temp;
                if (userId > friendId) {
                    temp = userId;
                    userId = friendId;
                    friendId = temp;
                }
                friendDto.setUserId(userId);
                friendDto.setFriendId(friendId);
                friendDto.setState(1);
                friendRepository.save(friendDto);
                return RestResultGenerator.genResult(friendDto, "同意添加成功");
            case -1: //拒绝
                tempFriendDto.setState(-1);
                tempFriendRepository.saveAndFlush(tempFriendDto);
                break;
        }
        return RestResultGenerator.genResult(null, "对方拒绝添加好友");
    }

}
