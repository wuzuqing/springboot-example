/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserRepository
 * Author:   ChiMon
 * Date:     2018/5/10 17:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.neeson.example.repository;

import com.neeson.example.entity.GameAccountTaskRecordDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ChiMon
 * @create 2018/5/10
 * @since 1.0.0
 */
public interface GameAccountTaskRecordRepository extends JpaRepository<GameAccountTaskRecordDto, Integer>, JpaSpecificationExecutor<GameAccountTaskRecordDto> {

    GameAccountTaskRecordDto findGameAccountDtoByAccountAndAreaAndFinishData(String account, String  area,String finishData);

    List<GameAccountTaskRecordDto> findGameAccountDtoByAreaAndFinishData(String  area, String finishData);

}
