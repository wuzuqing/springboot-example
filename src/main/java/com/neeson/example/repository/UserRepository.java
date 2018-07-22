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

import com.neeson.example.entity.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ChiMon
 * @create 2018/5/10
 * @since 1.0.0
 */
public interface UserRepository extends JpaRepository<UserDto, Integer>, JpaSpecificationExecutor<UserDto> {

     UserDto findByAccountOrPhone(String account,String phone);
     UserDto findByAccount(String account);
     UserDto findByAccountAndPassword(String account, String password);


     //@Modifying
     //@Query("update UserDto u set u.userName = ?1 where c.id = ?2")
     //int modifyByIdAndUserId(String  userName, Long id);
     //
     //@Transactional
     //@Modifying
     //@Query("delete from UserDto where id = ?1")
     //void deleteByUserId(Long id);
     //
     //@Transactional(timeout = 10)
     //@Query("select u from UserDto u where u.emailAddress = ?1")
     //UserDto findByEmailAddress(String emailAddress);


     @Query(value = "select * from user u where u.id in (:spIds)", nativeQuery = true)
     List<UserDto> findById(@Param("spIds") List<Integer> spIds);
}
