package com.neeson.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: neeson
 * Date: 2018/3/30
 * Time: 17:52
 * Description:
 */
@Api("测试接口")
@RestController
@RequestMapping("/other")
public class OtherController {


    @ApiOperation(value = "获取支付url", produces = "application/json")
    @RequestMapping(value = "/getUrl", method = RequestMethod.GET)
    public String getUrl(HttpServletRequest request) {

//        var ua = window.navigator.userAgent.toLowerCase();
//        //判断是不是微信
//        if ( ua.match(/MicroMessenger/i) == 'micromessenger' ) {
//            return "WeiXIN";
//        }
//        //判断是不是支付宝
//        if (ua.match(/AlipayClient/i) == 'alipayclient') {
//            return "Alipay";
//        }
//        //哪个都不是
//        return "false";

        String userAgent = request.getHeader("user-agent");
        if (userAgent != null && userAgent.contains("MicroMessenger")){
            System.out.println("微信支付");
        }else if (userAgent !=null && userAgent.contains("AlipayClient")){
            System.out.println("支付宝支付");
        }
        System.out.println(userAgent);
        return userAgent;
//        return RestResultGenerator.genResult("http://www.baidu.com", "获取成功");
//        return "http://www.baidu.com";
    }

}
