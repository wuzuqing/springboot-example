package com.neeson.example.controller;

import com.neeson.example.service.impl.GameServiceImpl;
import com.neeson.example.util.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: neeson
 * Date: 2018/3/30
 * Time: 17:52
 * Description:
 */
@Api("游戏相关")
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameServiceImpl gameService;


    @ApiOperation(value = "添加进入完成任务", produces = "application/json")
    @RequestMapping(value = "/updateTaskRecord", method = RequestMethod.POST)
    public ResponseResult updateTaskRecord(@ApiParam(value = "账号", required = true) @RequestParam String account,
                                           @ApiParam(value = "区号", required = true) @RequestParam String area,
                                           @ApiParam(value = "完成任务", required = true) @RequestParam String task
    ) {
        return gameService.updateTaskRecord(account, area, task);
    }


    @ApiOperation(value = "获取今日完成任务", produces = "application/json")
    @RequestMapping(value = "/getTaskRecord", method = RequestMethod.GET)
    public ResponseResult getTaskRecord(@ApiParam(value = "账号", required = true) @RequestParam String account,
                                        @ApiParam(value = "区号", required = true) @RequestParam String area
    ) {
        return gameService.getTaskRecord(account, area);
    }

    @ApiOperation(value = "获取今日完成任务", produces = "application/json")
    @RequestMapping(value = "/getTaskRecords", method = RequestMethod.GET)
    public ResponseResult getTaskRecords(@ApiParam(value = "区号", required = true) @RequestParam String area
    ) {
        return gameService.getTaskRecord(area);
    }


    @ApiOperation(value = "更新坐标及颜色", produces = "application/json")
    @RequestMapping(value = "/updatePoints", method = RequestMethod.POST)
    public ResponseResult updatePoints(@ApiParam(value = "标记 width_height", required = true) @RequestParam String flag,
                                       @ApiParam(value = "类型", required = true) @RequestParam String type,
                                       @ApiParam(value = "坐标集合", required = true) @RequestParam String points
    ) {
        return gameService.updatePoints(flag, type, points);
    }

    @ApiOperation(value = "获取进入完成任务", produces = "application/json")
    @RequestMapping(value = "/getPoints", method = RequestMethod.GET)
    public ResponseResult getPoints(@ApiParam(value = "标记 width_height", required = true) @RequestParam String flag
    ) {
        return gameService.getPoints(flag);
    }
    @ApiOperation(value = "获取进入完成任务", produces = "application/json")
    @RequestMapping(value = "/getItemCoord", method = RequestMethod.GET)
    public ResponseResult getItemCoord(@ApiParam(value = "标记 width_height", required = true) @RequestParam String flag,
                                       @ApiParam(value = "路径 page", required = true) @RequestParam String page
    ) {
        return gameService.getItemCoord(flag,page);
    }

    @ApiOperation(value = "更新页面数据", produces = "application/json")
    @RequestMapping(value = "/updatePageData", method = RequestMethod.POST)
    public ResponseResult updatePageData(@ApiParam(value = "标记 width_height", required = true) @RequestParam String flag,
                                       @ApiParam(value = "页面", required = true) @RequestParam String page,
                                       @ApiParam(value = "坐标数据", required = true) @RequestParam String data
    ) {
        return gameService.updatePageData(flag, page, data);
    }

}
