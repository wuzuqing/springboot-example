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

import com.neeson.example.entity.GamePointDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 * @author ChiMon
 * @create 2018/5/10
 * @since 1.0.0
 */
public interface GamePointRepository extends JpaRepository<GamePointDto, String>, JpaSpecificationExecutor<GamePointDto> {

}
