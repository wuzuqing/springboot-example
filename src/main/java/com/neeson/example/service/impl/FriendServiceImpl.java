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
public class FriendServiceImpl implements IFriendService {


    @Autowired
    private TempFriendRepository tempFriendRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getFriendList(Integer id) {
        List<FriendDto> friendDtoDTOS1 = friendRepository.findByUserIdOrFriendId(id, id);
        if (friendDtoDTOS1 == null || friendDtoDTOS1.isEmpty()) {
            return null;
        }
        List<Integer> ids = new ArrayList<>();
        for (FriendDto friendDTO : friendDtoDTOS1) {
            //双方都删除对方了,则都不返回
            if (friendDTO.getDeleteSum() == (friendDTO.getUserId() + friendDTO.getFriendId())) {
                continue;
            } else if (friendDTO.getUserId() == id) {
                if (friendDTO.getDeleteSum() != friendDTO.getFriendId()) {
                    ids.add(friendDTO.getFriendId());
                }
            } else {
                if (friendDTO.getDeleteSum() != friendDTO.getUserId()) {
                    ids.add(friendDTO.getUserId());
                }
            }
        }
        if (ids.isEmpty()){
            return null;
        }
        return userRepository.findById(ids);
    }

    @Override
    public TempFriendDto addFriend(Integer userId, Integer friendId) { //userId 小,friendId 大
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
        return friendDtoDTO;
    }

    @Override
    public FriendDto deleteFriend(Integer userId, Integer friendId) {

        int deleteId = friendId;
        int tempId = 0;
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

        return friendDto;
    }

    @Override
    public FriendDto reAddFriend(Integer userId, Integer friendId, Integer state) {
        TempFriendDto tempFriendDto = tempFriendRepository.findByUserIdAndFriendId(userId, friendId);
        if (tempFriendDto == null) return null;
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
                tempFriendRepository.delete(tempFriendDto);
                return friendDto;
            case -1: //拒绝
                tempFriendDto.setState(-1);
                tempFriendRepository.saveAndFlush(tempFriendDto);
                return null;
        }
        return null;
    }

}
