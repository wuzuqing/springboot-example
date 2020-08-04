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

import com.neeson.example.entity.DltCpDto;
import com.neeson.example.entity.DltCpExtendDto;
import com.neeson.example.repository.DltCpExtendRepository;
import com.neeson.example.repository.DltCpRepository;
import com.neeson.example.service.IOtherService;
import com.neeson.example.util.cp.CaiPiaoDaletou;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
public class OtherServiceImpl implements IOtherService {


    @Autowired
    DltCpRepository dltCpRepository;
    @Autowired
    DltCpExtendRepository dltCpExtendRepository;


    @Override
    public void updateRecord(String tag) {
        if (ObjectUtils.equals(tag, "dlt")) {
            CaiPiaoDaletou caiPiaoDaletou = new CaiPiaoDaletou(dltCpRepository,dltCpExtendRepository);
            caiPiaoDaletou.updateRecord(1);
        }
    }

    @Override
    public int updateCpExtend(String tag) {
        if (ObjectUtils.equals(tag, "dlt")) {
            List<DltCpDto> dltCpDtos = dltCpRepository.findAll();
            List<DltCpExtendDto> result = new ArrayList<>();
            DltCpExtendDto dto;
            for (DltCpDto cpDto : dltCpDtos) {
                dto = new DltCpExtendDto(cpDto);
                result.add(dto);
            }
            dltCpExtendRepository.saveAll(result);
            return result.size();
        }
        return 0;
    }
}
