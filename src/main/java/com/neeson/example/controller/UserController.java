package com.neeson.example.controller;

import com.neeson.example.dto.UserDTO;
import com.neeson.example.service.impl.UserServiceImpl;
import com.neeson.example.util.response.ResponseResult;
import com.neeson.example.util.response.RestResultGenerator;
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
@Api("用户相关")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;



    @ApiOperation(value = "获取用户信息", produces = "application/json")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ResponseResult getUserInfo(@ApiParam(value = "用户ID", required = true) @PathVariable Long id) {
        UserDTO user = userService.getUser(Math.toIntExact(id));
        return RestResultGenerator.genResult(user, "获取成功");
    }


    @ApiOperation(value = "搜索用户", produces = "application/json")
    @RequestMapping(value = "/search/{phone}", method = RequestMethod.GET)
    public ResponseResult searchUser(@ApiParam(value = "用户名", required = true) @PathVariable String phone)
                                   {
        UserDTO user = userService.searchUser(phone);
        if (user == null) {
            return RestResultGenerator.genErrorResult("没有此用户,请重新输入");
        } else {
            return RestResultGenerator.genResult(user, "搜索成功");
        }

    }


    @ApiOperation(value = "注册", produces = "application/json")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseResult register(@ApiParam(value = "手机号", required = true) @RequestParam String phone,
                                   @ApiParam(value = "密码", required = true) @RequestParam String pwd) {
        UserDTO user = userService.register(phone, pwd);
        if (user == null) {
            return RestResultGenerator.genErrorResult("该手机号已注册,请更换手机号码再试");
        } else {
            return RestResultGenerator.genResult(user, "注册成功");
        }

    }

    @ApiOperation(value = "登录", produces = "application/json")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@ApiParam(value = "手机号", required = true) @RequestParam String phone,
                                @ApiParam(value = "密码", required = true) @RequestParam String pwd) {
        UserDTO user = userService.login(phone, pwd);
        if (user == null) {
            return RestResultGenerator.genErrorResult("登录失败,该手机号尚未注册");
        } else {
            if (user.getPwd().equals(pwd)){
                return RestResultGenerator.genResult(user, "登录成功");
            }else{
                return RestResultGenerator.genErrorResult(  "密码错误");
            }

        }
    }

}
