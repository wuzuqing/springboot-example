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

import com.neeson.example.entity.GroupDto;
import com.neeson.example.entity.GroupMemberDto;
import com.neeson.example.entity.TempFriendDto;
import com.neeson.example.repository.GroupMemberRepository;
import com.neeson.example.repository.GroupRepository;
import com.neeson.example.repository.TempFriendRepository;
import com.neeson.example.service.IGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GroupServiceImpl implements IGroupService {


    @Autowired
    private TempFriendRepository tempFriendRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;


    @Override
    public GroupDto createGroup(Integer userId, String groupName) {
        GroupDto groupDto = new GroupDto();
        groupDto.setGroupName(groupName);
        groupDto.setGroupManagerId(userId);
        groupDto.setCreateTime(System.currentTimeMillis());
        groupRepository.save(groupDto);

        GroupMemberDto groupMemberDto = new GroupMemberDto();
        groupMemberDto.setGroupId(groupDto.getId());
        groupMemberDto.setUserId(userId);
        groupMemberDto.setShowGroupNickName(false);
        groupMemberDto.setIsTop(false);
        groupMemberDto.setDisturb(false);

        groupMemberRepository.save(groupMemberDto);

        return groupDto;
    }

    @Override
    public TempFriendDto invitationUserToGroup(Integer groupId, Integer userId) {
        GroupMemberDto groupMemberDto = groupMemberRepository.findByUserIdAndGroupId(userId, groupId);
        if (groupMemberDto == null) {  //已经在群里
            TempFriendDto tempFriendDto = tempFriendRepository.findByUserIdAndGroupId(userId, groupId);
            if (tempFriendDto == null) {
                tempFriendDto = new TempFriendDto();
                tempFriendDto.setUserId(userId);
                tempFriendDto.setGroupId(groupId);
                tempFriendDto.setState(0);
                tempFriendRepository.save(tempFriendDto);
            }
            return tempFriendDto;
        }
        return null;
    }

    @Override
    public GroupDto jumpToGroup(Integer groupId, Integer userId, Integer status) {
        TempFriendDto tempFriendDto = tempFriendRepository.findByUserIdAndGroupId(userId, groupId);
        if (tempFriendDto != null) {
            tempFriendDto.setState(status);
            if (status == 1) {
                GroupMemberDto groupMemberDto = new GroupMemberDto();
                groupMemberDto.setGroupId(groupId);
                groupMemberDto.setUserId(userId);
                groupMemberDto.setShowGroupNickName(false);
                groupMemberDto.setIsTop(false);
                groupMemberDto.setDisturb(false);
                groupMemberRepository.save(groupMemberDto);
            } else {

            }
        }
        return null;
    }

    @Override
    public List<GroupDto> getGroupList(Integer userId) {
        List<GroupMemberDto> groupMemberDtos = groupMemberRepository.findByUserId(userId);
        List<Integer> ids = new ArrayList<>();
        for (GroupMemberDto memberDto : groupMemberDtos) {
            ids.add(memberDto.getGroupId());
        }
        if (ids.isEmpty()) {
            return null;
        }
        List<GroupDto> groupDtos = groupRepository.findById(ids);
        return groupDtos;
    }

    @Override
    public List<GroupMemberDto> getGroupMemberList(Integer groupId, Integer userId) {
        List<GroupMemberDto> groupMemberDtos = groupMemberRepository.findByGroupId(groupId);
        for (int i = groupMemberDtos.size() - 1; i >= 0; i--) {
            if (groupMemberDtos.get(i).getUserId() == userId) {
                groupMemberDtos.remove(i);
                break;
            }
        }
        return groupMemberDtos;
    }
}
