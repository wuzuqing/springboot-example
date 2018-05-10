/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FriendRepository
 * Author:   ChiMon
 * Date:     2018/5/10 17:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.neeson.example.repository;

import com.neeson.example.dto.FriendDTO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ChiMon
 * @create 2018/5/10
 * @since 1.0.0
 */
public interface FriendRepository  extends JpaRepository<FriendDTO, Long> {



}
