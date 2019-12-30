package com.neeson.example.controller;

import com.neeson.example.entity.UserDto;
import com.neeson.example.service.impl.UserServiceImpl;
import com.neeson.example.util.AddressUtils;
import com.neeson.example.util.CusAccessObjectUtil;
import com.neeson.example.util.response.ResponseResult;
import com.neeson.example.util.response.RestResultGenerator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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


    @ApiOperation(value = "获取地址", produces = "application/json")
    @RequestMapping(value = "/getServerAddress", method = RequestMethod.GET)
    public ResponseResult getServerAddress(HttpServletRequest request) {
        String remoteAddr = CusAccessObjectUtil.getIpAddress(request);
        System.out.println("remoteAddr:" + remoteAddr);
        String addresses = AddressUtils.getAddresses(remoteAddr);
        System.out.println("addresses:" + addresses);
        return RestResultGenerator.genResult(addresses, "获取成功");
    }


    @ApiOperation(value = "获取用户信息", produces = "application/json")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ResponseResult getUserInfo(@ApiParam(value = "用户ID", required = true) @PathVariable Long id) {
        return userService.getUser(Math.toIntExact(id));
    }


    @ApiOperation(value = "获取用户信息", produces = "application/json")
    @RequestMapping(value = "/getUserInfoByToken/{token}", method = RequestMethod.GET)
    public ResponseResult getUserInfoByToken(@ApiParam(value = "用户ID", required = true) @PathVariable String token) {
        return userService.getUserInfoByToken(token );
    }


    @ApiOperation(value = "搜索用户", produces = "application/json")
    @RequestMapping(value = "/search/{phone}", method = RequestMethod.GET)
    public ResponseResult searchUser(@ApiParam(value = "用户名", required = true) @PathVariable String phone) {
        return userService.searchUser(phone);
    }


    @ApiOperation(value = "注册", produces = "application/json")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseResult register(@ApiParam(value = "手机号", required = true) @RequestParam String phone,
                                   @ApiParam(value = "密码", required = true) @RequestParam String pwd) {
        return userService.register(phone, pwd);
    }

    @ApiOperation(value = "登录", produces = "application/json")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@ApiParam(value = "手机号", required = true) @RequestParam String phone,
                                @ApiParam(value = "密码", required = true) @RequestParam String pwd) {
        return userService.login(phone, pwd);
    }

}
