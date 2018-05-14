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

import com.neeson.example.dto.FriendDTO;
import com.neeson.example.dto.UserDTO;
import com.neeson.example.repository.FriendRepository;
import com.neeson.example.repository.UserRepository;
import com.neeson.example.service.IFriendService;
import io.rong.RongCloud;
import io.rong.messages.TxtMessage;
import io.rong.models.message.SystemMessage;
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
    private FriendRepository friendRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getFriendList(Integer id) {
        List<FriendDTO> friendDTOS1 = friendRepository.findByUserIdOrFriendId(id, id);
        if (friendDTOS1 == null || friendDTOS1.isEmpty()) {
            return null;
        }
        List<Integer> ids = new ArrayList<>();
        for (FriendDTO friendDTO : friendDTOS1) {
            if (friendDTO.getFlag() == 0) continue;//还没同意
            if (friendDTO.getFriendId() == id) {
                ids.add(friendDTO.getUserId());
            } else {
                ids.add(friendDTO.getFriendId());
            }
        }
        return userRepository.findById(ids);
    }

    @Override
    public FriendDTO addFriend(Integer userId, Integer friendId) { //userId 小,friendId 大
        boolean isSwap = false;
        if (userId > friendId) {
            int temp = userId;
            userId = friendId;
            friendId = temp;
            isSwap = true;
        }
        FriendDTO friendDTO = friendRepository.findByUserIdAndFriendId(userId, friendId);
        if (friendDTO == null) {            //第一次添加好友
            friendDTO = new FriendDTO();
            friendDTO.setUserId(userId);
            friendDTO.setFlag(0);
            friendDTO.setFriendId(friendId);
            friendRepository.save(friendDTO);
            //发通知给添加的人
            if (isSwap) {
                sendSystemMessage(friendId, userId, 0);
            } else {
                sendSystemMessage(userId, friendId, 0);
            }

        } else {      //同意对方添加好友
            if (friendDTO.getFlag() == 0){
                friendDTO.setFlag(1);
                friendRepository.save(friendDTO);
                //发通知给
                if (isSwap) {
                    sendSystemMessage(friendId, userId, 1);
                } else {
                    sendSystemMessage(userId, friendId, 1);
                }
            }


        }
        return friendDTO;
    }

    private void sendSystemMessage(Integer userId, Integer friendId, int flag) {
        SystemMessage messageModel = new SystemMessage();
        try {
            Optional<UserDTO> userDTO = userRepository.findById(userId);
            String name = "";
            if (userDTO.isPresent()) {
                name = userDTO.get().getUserName();
            }
            TxtMessage txtMessage = new TxtMessage(name + "将你添加为好友,现在可以开始聊天了", String.valueOf(flag));
            messageModel.setSenderId(String.valueOf(userId));
            messageModel.setTargetId(new String[]{String.valueOf(friendId)});
            messageModel.setContent(txtMessage);
            messageModel.setObjectName(txtMessage.getType());
            RongCloud.getInstance().message.system.send(messageModel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
