package com.neeson.example.controller;

import com.neeson.example.dto.UserDTO;
import com.neeson.example.im.IMService;
import com.neeson.example.util.response.ResponseResult;
import com.neeson.example.util.response.RestResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private IMService imService;



    @ApiOperation("获取好友列表")
    @ApiImplicitParam (name = "id",value = "用户ID",required = true,paramType = "path",dataType = "String")
    @GetMapping("/getFriend/{id}")
    public ResponseResult getFriend(@PathVariable String id){
        System.out.print("id:"+id);
        List<UserDTO> friendList  = imService.getFriendList(id);
        return RestResultGenerator.genResult(friendList,"");
    }


    @ApiOperation("获取好友列表")
    @ApiImplicitParam (name = "id",value = "用户ID",required = true,paramType = "path",dataType = "String")
    @GetMapping("/getGroupList/{id}")
    public ResponseResult getGroupList(@PathVariable String id){
        System.out.print("id:"+id);
        List<UserDTO> friendList  = imService.getFriendList(id);
        return RestResultGenerator.genResult(friendList,"");
    }

    @ApiOperation("获取用户信息")
    @ApiImplicitParam (name = "id",value = "用户ID",required = true,paramType = "path",dataType = "Long")
    @GetMapping("/info/{id}")
    public ResponseResult getUserInfo(@PathVariable Long id){

        UserDTO user = imService.getUser(Math.toIntExact(id));
        return RestResultGenerator.genResult(user,"");
    }







}
