package com.neeson.example.controller;

import com.neeson.example.dto.FriendDTO;
import com.neeson.example.dto.UserDTO;
import com.neeson.example.service.impl.FriendServiceImpl;
import com.neeson.example.util.response.ResponseResult;
import com.neeson.example.util.response.RestResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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



    @ApiOperation("获取好友列表")
    @ApiImplicitParam (name = "id",value = "用户ID",required = true,paramType = "path",dataType = "String")
    @GetMapping("/getFriend/{id}")
    public ResponseResult getFriend(@PathVariable String id){
        System.out.print("id:"+id);
        List<UserDTO> friendList  = friendServicer.getFriendList(Integer.valueOf(id));
        if (friendList==null){
            return RestResultGenerator.genErrorResult("您目前好友列表为空");
        }else{
            return RestResultGenerator.genResult(friendList,"");
        }

    }


    @ApiOperation("获取好友列表")
    @ApiImplicitParam (name = "id",value = "用户ID",required = true,paramType = "path",dataType = "String")
    @GetMapping("/getGroupList/{id}")
    public ResponseResult getGroupList(@PathVariable String id){
        System.out.print("id:"+id);
        List<UserDTO> friendList  = friendServicer.getFriendList(Integer.valueOf(id));

        return RestResultGenerator.genResult(friendList,"");
    }



    @ApiOperation(value = "添加好友", produces = "application/json")
    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public ResponseResult addFriend(@ApiParam(value = "用户ID", required = true) @RequestParam Integer userId,
                                @ApiParam(value = "好友ID", required = true) @RequestParam Integer friendId) {
        FriendDTO user = friendServicer.addFriend(userId, friendId);

        return RestResultGenerator.genResult(user,"添加成功");
    }


}
