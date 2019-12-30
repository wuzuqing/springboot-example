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

import com.neeson.example.entity.ChatMsgDto;
import com.neeson.example.repository.ChatMsgRepository;
import com.neeson.example.repository.GroupMemberRepository;
import com.neeson.example.service.IMessageService;
import com.neeson.example.util.ResultUtil;
import com.neeson.example.util.response.ResponseResult;
import com.neeson.example.util.response.RestResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
public class MessageServiceImpl implements IMessageService {


    @Autowired
    ChatMsgRepository chatMsgRepository;
    @Autowired
    GroupMemberRepository groupMemberRepository;

    @Override
    public ResponseResult getOffLineMsg(String userId, Long lastTime) {
        List<ChatMsgDto> friendOffMsgList = chatMsgRepository.findFriendOffMsgList(lastTime, Integer.parseInt(userId));
        List<ChatMsgDto> groupOffMsgList = chatMsgRepository.findGroupOffMsgList(lastTime);

        if (!ResultUtil.checkIsEmpty(groupOffMsgList)) {
            List<Integer> groupIds = groupMemberRepository.findGroupIdByUserId(Integer.valueOf(userId));
            boolean isEmpty = ResultUtil.checkIsEmpty(groupIds);
            for (int i = groupOffMsgList.size() - 1; i >= 0; i--) {
                ChatMsgDto msgDto = groupOffMsgList.get(i);
                if (msgDto.getChatType() == 1 && (isEmpty || ResultUtil.checkNotIn(groupIds, msgDto.getGroup_id()))) {
                    groupOffMsgList.remove(i);
                }
            }
            if (ResultUtil.checkNotEmpty(groupOffMsgList)) {
                friendOffMsgList.addAll(groupOffMsgList);
                Collections.sort(friendOffMsgList); //排序
            }
        }
        return RestResultGenerator.genResult(friendOffMsgList, "获取成功");
    }
}
