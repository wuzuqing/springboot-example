package com.neeson.example.controller;

import com.neeson.example.entity.*;
import com.neeson.example.service.impl.FriendServiceImpl;
import com.neeson.example.service.impl.GroupServiceImpl;
import com.neeson.example.util.response.ResponseResult;
import com.neeson.example.util.response.RestResultGenerator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: neeson
 * Date: 2018/3/30
 * Time: 17:52
 * Description:
 */
@Api("聊天相关")
@RestController
@RequestMapping("/chat")
public class IMController {

    @Autowired
    private FriendServiceImpl friendServicer;


    @Autowired
    private GroupServiceImpl groupService;

    @ApiOperation("获取好友列表")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "String")
    @GetMapping("/getFriend/{id}")
    public ResponseResult getFriend(@PathVariable String id) {
        List<UserDto> friendList = friendServicer.getFriendList(Integer.valueOf(id));
        if (friendList == null) {
            return RestResultGenerator.genErrorResult("您目前好友列表为空");
        } else {
            return RestResultGenerator.genResult(friendList, "");
        }

    }


    @ApiOperation(value = "根据群ID获取群成员列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "群ID", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "path", dataType = "String")
    })

    @GetMapping("/getGroupMemberList/{groupId}/{userId}")
    public ResponseResult getGroupMemberList(@PathVariable String groupId, @PathVariable String userId) {
        List<GroupMemberDto> groupList = groupService.getGroupMemberList(Integer.parseInt(groupId), Integer.parseInt
                (userId));
        return RestResultGenerator.genResult(groupList, "");
    }

    @ApiOperation("根据用户ID获取群列表")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "String")
    @GetMapping("/getGroupList/{id}")
    public ResponseResult getGroupList(@PathVariable String id) {
        List<GroupDto> groupList = groupService.getGroupList(Integer.valueOf(id));
        return RestResultGenerator.genResult(groupList, "");
    }


    @ApiOperation(value = "添加好友", produces = "application/json")
    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public ResponseResult addFriend(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                    @ApiParam(value = "好友ID", required = true) @RequestParam Integer friendId) {
        TempFriendDto user = friendServicer.addFriend(userId, friendId);
        if (user == null) {
            return RestResultGenerator.genResult(null, "申请添加好友失败,该好友不是有效的用户");
        } else {

            return RestResultGenerator.genResult(user, "申请添加好友成功");
        }
    }



    @ApiOperation(value = "是否同意添加好友", produces = "application/json")
    @RequestMapping(value = "/reAddFriend", method = RequestMethod.POST)
    public ResponseResult reAddFriend(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                      @ApiParam(value = "好友ID", required = true) @RequestParam Integer friendId,
                                      @ApiParam(value = "状态", required = true) @RequestParam Integer status) {
        FriendDto user = friendServicer.reAddFriend(userId, friendId, status);
        if (user == null) {
            return RestResultGenerator.genResult(null, "对方拒绝添加好友");
        } else {
            return RestResultGenerator.genResult(user, "同意添加成功");
        }

    }

    @ApiOperation(value = "删除好友", produces = "application/json")
    @RequestMapping(value = "/deleteFriend", method = RequestMethod.POST)
    public ResponseResult deleteFriend(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                    @ApiParam(value = "好友ID", required = true) @RequestParam Integer friendId) {
        FriendDto deleteFriend = friendServicer.deleteFriend(userId, friendId);

        if (deleteFriend == null) {
            return RestResultGenerator.genResult(null, "申请添加好友失败,该好友不是有效的用户");
        } else {

            return RestResultGenerator.genResult(deleteFriend, "申请添加好友成功");
        }
    }



    @ApiOperation(value = "创建群组", produces = "application/json")
    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public ResponseResult createGroup(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                      @ApiParam(value = "群名", required = true) @RequestParam String name) {
        GroupDto group = groupService.createGroup(userId, name);
        return RestResultGenerator.genResult(group, "创建群组成功");
    }

    @ApiOperation(value = "邀请朋友进群", produces = "application/json")
    @RequestMapping(value = "/invitationUserToGroup", method = RequestMethod.POST)
    public ResponseResult invitationUserToGroup(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                                @ApiParam(value = "群Id", required = true) @RequestParam Integer groupId) {
        TempFriendDto tempFriendDto = groupService.invitationUserToGroup(groupId, userId);
        if (tempFriendDto == null) {
            return RestResultGenerator.genResult(tempFriendDto, "你已邀请该好友");
        }
        return RestResultGenerator.genResult(tempFriendDto, "邀请朋友成功");
    }

    @ApiOperation(value = "是否同意进群", produces = "application/json")
    @RequestMapping(value = "/jumpToGroup", method = RequestMethod.POST)
    public ResponseResult jumpToGroup(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                      @ApiParam(value = "群Id", required = true) @RequestParam Integer groupId,
                                      @ApiParam(value = "状态", required = true) @RequestParam Integer status) {
        GroupDto groupDto = groupService.jumpToGroup(groupId, userId, status);
        if (groupDto == null) {
            return RestResultGenerator.genResult(groupDto, "你已邀请该好友");
        }
        return RestResultGenerator.genResult(groupDto, "邀请朋友成功");
    }
}
