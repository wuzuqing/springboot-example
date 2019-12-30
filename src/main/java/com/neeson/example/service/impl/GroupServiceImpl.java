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
import com.neeson.example.repository.UserRepository;
import com.neeson.example.service.IGroupService;
import com.neeson.example.util.ResultUtil;
import com.neeson.example.util.response.ResponseResult;
import com.neeson.example.util.response.RestResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseResult createGroup(Integer userId, String groupName) {
        GroupDto groupDto = new GroupDto();
        groupDto.setGroupName(groupName);
        groupDto.setGroupManagerId(userId);
        groupDto.setCreateTime(System.currentTimeMillis());
        groupRepository.save(groupDto);

        GroupMemberDto groupMemberDto = new GroupMemberDto();
        groupMemberDto.setGroup(groupDto);
        groupMemberDto.setUser(userRepository.getOne(userId));
        groupMemberDto.setShowGroupNickName(false);
        groupMemberDto.setTop(false);
        groupMemberRepository.save(groupMemberDto);
        return RestResultGenerator.genResult(groupDto, "创建群组成功");
    }

    @Override
    public ResponseResult invitationUserToGroup(Integer groupId, Integer userId) {
        List<GroupMemberDto> memberDtos = groupMemberRepository.findByUserId(userId);
        if (checkNotInGroup(memberDtos, groupId)) { //如果没有在群里
            TempFriendDto tempFriendDto = tempFriendRepository.findByUserIdAndGroupId(userId, groupId);
            if (tempFriendDto == null) {
                tempFriendDto = new TempFriendDto();
                tempFriendDto.setUserId(userId);
                tempFriendDto.setGroupId(groupId);
                tempFriendDto.setState(0);
                tempFriendRepository.save(tempFriendDto);
            }
            return RestResultGenerator.genResult(tempFriendDto, "邀请朋友成功");
        }
        return RestResultGenerator.genResult(null, "你已邀请该好友");
    }

    private boolean checkNotInGroup(List<GroupMemberDto> memberDtos, Integer groupId) {
        if (memberDtos == null || memberDtos.isEmpty()) return true;
        for (GroupMemberDto memberDto : memberDtos) {
            if (memberDto.getGroup().getId() == groupId) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ResponseResult jumpToGroup(Integer groupId, Integer userId, Integer status) {
        TempFriendDto tempFriendDto = tempFriendRepository.findByUserIdAndGroupId(userId, groupId);
        if (tempFriendDto != null) {
            tempFriendDto.setState(status);
            if (status == 1) {
                GroupMemberDto groupMemberDto = new GroupMemberDto();
                groupMemberDto.setGroup(groupRepository.getOne(groupId));
                groupMemberDto.setUser(userRepository.getOne(userId));
                groupMemberDto.setShowGroupNickName(false);
                groupMemberDto.setTop(false);
                groupMemberDto.setDisturb(false);
                groupMemberRepository.save(groupMemberDto);
                return RestResultGenerator.genResult(groupMemberDto, "邀请朋友成功");
            }
        }
        return RestResultGenerator.genResult(null, "你已邀请该好友");
    }

    @Override
    public ResponseResult getGroupList(Integer userId) {
        List<GroupDto> groupDtos = groupRepository.findByUserId(userId);
        if (ResultUtil.checkIsEmpty(groupDtos)) {
            return RestResultGenerator.genErrorResult("获取数据失败");
        }
        return RestResultGenerator.genResult(groupDtos, "");
    }

    @Override
    public ResponseResult getGroupMemberList(Integer groupId, Integer userId) {
        List<GroupMemberDto> groupMemberDtos = groupMemberRepository.findByGroupId(groupId);
        if (groupMemberDtos == null || groupMemberDtos.isEmpty()) return RestResultGenerator.genResult(null, "获取数据失败");
        for (GroupMemberDto memberDto : groupMemberDtos) {
            if (memberDto.getGroup() != null) {
                memberDto.setgId(memberDto.getGroup().getId());
                memberDto.setGroupName(memberDto.getGroup().getGroupName());
                memberDto.setGroupManagerId(memberDto.getGroup().getGroupManagerId());
            }
            if (memberDto.getUser() != null) {
                memberDto.setuId(memberDto.getUser().getId());
                memberDto.setNick(memberDto.getUser().getNick());
                memberDto.setAvatar(memberDto.getUser().getAvatar());
                memberDto.setSex(memberDto.getUser().getSex());
            }
        }
        return RestResultGenerator.genResult(groupMemberDtos, "获取成功");
    }
}
