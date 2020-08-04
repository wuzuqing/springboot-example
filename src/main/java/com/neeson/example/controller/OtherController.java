package com.neeson.example.controller;

import com.neeson.example.service.impl.OtherServiceImpl;
import com.neeson.example.util.response.ResponseResult;
import com.neeson.example.util.response.RestResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private OtherServiceImpl otherService;


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
        String result = "";
        if (userAgent != null && userAgent.contains("MicroMessenger")) {
            System.out.println("微信支付");
            result = "wxp://f2f00ZBs-ncNO-Mvy3HW-b8lG_tzi5afJeqE";
        } else if (userAgent != null && userAgent.contains("AlipayClient")) {
            System.out.println("支付宝支付");
            result = "https://qr.alipay.com/fkx16025adjjxizcmcdun77";
        }
        System.out.println(userAgent);
        return result;
//        return RestResultGenerator.genResult("http://www.baidu.com", "获取成功");
//        return "http://www.baidu.com";
    }

    @ApiOperation(value = "更新彩票记录", produces = "application/json")
    @RequestMapping(value = "/updateCpRecord/{tag}", method = RequestMethod.POST)
    public ResponseResult updateCpRecord(@PathVariable String tag) {
        otherService.updateRecord(tag);
        return RestResultGenerator.genResult("success", "获取成功");
    }

    @ApiOperation(value = "更新彩票五行记录", produces = "application/json")
    @RequestMapping(value = "/updateCpExtend/{tag}", method = RequestMethod.POST)
    public ResponseResult updateCpExtend(@PathVariable String tag) {
        otherService.updateCpExtend(tag);
        return RestResultGenerator.genResult("success", "获取成功");
    }
}
