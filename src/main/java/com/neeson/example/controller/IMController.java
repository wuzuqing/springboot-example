package com.neeson.example.controller;

import com.neeson.example.service.impl.FriendServiceImpl;
import com.neeson.example.service.impl.GroupServiceImpl;
import com.neeson.example.service.impl.MessageServiceImpl;
import com.neeson.example.util.response.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private MessageServiceImpl messageService;
    @Autowired
    private GroupServiceImpl groupService;

    @ApiOperation("获取好友列表")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "String")
    @GetMapping("/getFriend/{id}")
    public ResponseResult getFriend(@PathVariable String id) {
        return friendServicer.getFriendList(Integer.valueOf(id));
    }

    @ApiOperation(value = "获取离线消息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "lastTime", value = "断开链接的时间", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping("/getOffLineMsg/{userId}/{lastTime}")
    public ResponseResult getOffLineMsg(@PathVariable String userId, @PathVariable Long  lastTime) {
        return messageService.getOffLineMsg(userId, lastTime);
    }


    @ApiOperation(value = "根据群ID获取群成员列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "群ID", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping("/getGroupMemberList/{groupId}/{userId}")
    public ResponseResult getGroupMemberList(@PathVariable String groupId, @PathVariable String userId) {
        return groupService.getGroupMemberList(Integer.parseInt(groupId), Integer.parseInt(userId));
    }

    @ApiOperation("根据用户ID获取群列表")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "String")
    @GetMapping("/getGroupList/{id}")
    public ResponseResult getGroupList(@PathVariable String id) {
        return groupService.getGroupList(Integer.valueOf(id));

    }


    @ApiOperation(value = "添加好友", produces = "application/json")
    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public ResponseResult addFriend(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                    @ApiParam(value = "好友ID", required = true) @RequestParam Integer friendId) {
        return friendServicer.addFriend(userId, friendId);
    }


    @ApiOperation(value = "是否同意添加好友", produces = "application/json")
    @RequestMapping(value = "/reAddFriend", method = RequestMethod.POST)
    public ResponseResult reAddFriend(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                      @ApiParam(value = "好友ID", required = true) @RequestParam Integer friendId,
                                      @ApiParam(value = "状态", required = true) @RequestParam Integer status) {
        return friendServicer.reAddFriend(userId, friendId, status);
    }

    @ApiOperation(value = "删除好友", produces = "application/json")
    @RequestMapping(value = "/deleteFriend", method = RequestMethod.POST)
    public ResponseResult deleteFriend(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                       @ApiParam(value = "好友ID", required = true) @RequestParam Integer friendId) {
        return friendServicer.deleteFriend(userId, friendId);
    }


    @ApiOperation(value = "创建群组", produces = "application/json")
    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public ResponseResult createGroup(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                      @ApiParam(value = "群名", required = true) @RequestParam String name) {
        return groupService.createGroup(userId, name);

    }

    @ApiOperation(value = "邀请朋友进群", produces = "application/json")
    @RequestMapping(value = "/invitationUserToGroup", method = RequestMethod.POST)
    public ResponseResult invitationUserToGroup(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                                @ApiParam(value = "群Id", required = true) @RequestParam Integer groupId) {
        return groupService.invitationUserToGroup(groupId, userId);

    }

    @ApiOperation(value = "是否同意进群", produces = "application/json")
    @RequestMapping(value = "/jumpToGroup", method = RequestMethod.POST)
    public ResponseResult jumpToGroup(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                      @ApiParam(value = "群Id", required = true) @RequestParam Integer groupId,
                                      @ApiParam(value = "状态", required = true) @RequestParam Integer status) {
       return groupService.jumpToGroup(groupId, userId, status);

    }



    public static void main(String[] args){
        System.out.println(System.currentTimeMillis());
    }
}
