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

import com.neeson.example.entity.GameAccountTaskRecordDto;
import com.neeson.example.entity.GamePointDto;
import com.neeson.example.repository.GameAccountTaskRecordRepository;
import com.neeson.example.repository.GamePointRepository;
import com.neeson.example.service.IGameService;
import com.neeson.example.util.RedisUtil;
import com.neeson.example.util.response.ResponseResult;
import com.neeson.example.util.response.RestResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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
public class GameServiceImpl implements IGameService {

    @Resource
    private RedisUtil redisUtil;


    @Autowired
    private GameAccountTaskRecordRepository recordRepository;


    @Autowired
    private GamePointRepository pointRepository;

    @Override
    public ResponseResult updateTaskRecord(String account, String area, String tasks) {
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        GameAccountTaskRecordDto model = recordRepository.findGameAccountDtoByAccountAndAreaAndFinishData(account, area, today);
        if (model != null) {
            model.setFinishTask(tasks);
        } else {
            model = new GameAccountTaskRecordDto();
            model.setAccount(account);
            model.setArea(area);
            model.setFinishTask(tasks);
            model.setFinishData(today);
            model.setPwd("123456");
        }
        recordRepository.saveAndFlush(model);
        return RestResultGenerator.genResult("success");

    }

    @Override
    public ResponseResult updatePoints(String flag, String type, String points) {
        Optional<GamePointDto> optional = pointRepository.findById(flag);
        GamePointDto model;
        if (optional.isPresent()) {
            model = optional.get();
        } else {
            model = new GamePointDto();
            model.setFlag(flag);
        }
        switch (type) {
            case "total":
                model.setTotalPoints(points);
                break;
            case "first":
                model.setFirstPoints(points);
                break;
            case "second":
                model.setSecondPoints(points);
                break;
            case "three":
                model.setThreePoints(points);
                break;
            case "four":
                model.setFourPoints(points);
                break;
            default:
                return RestResultGenerator.genErrorResult("not impl type");
        }
        pointRepository.saveAndFlush(model);
        return RestResultGenerator.genResult("success");
    }
}
