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

import com.neeson.example.repository.DltCpRepository;
import com.neeson.example.service.IOtherService;
import com.neeson.example.util.cp.CaiPiaoDaletou;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public void updateRecord(String tag) {
        if (ObjectUtils.equals(tag,"dlt")){
            CaiPiaoDaletou caiPiaoDaletou = new CaiPiaoDaletou(dltCpRepository);
            caiPiaoDaletou.updateRecord(1);
        }
    }
}
