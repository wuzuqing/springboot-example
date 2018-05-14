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

import com.neeson.example.dto.UserDTO;
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
public interface UserRepository extends JpaRepository<UserDTO, Integer>, JpaSpecificationExecutor<UserDTO> {

     UserDTO findByPhone(String phone);

     UserDTO findByPhoneAndPwd(String phone, String pwd);


     //@Modifying
     //@Query("update User u set u.userName = ?1 where c.id = ?2")
     //int modifyByIdAndUserId(String  userName, Long id);
     //
     //@Transactional
     //@Modifying
     //@Query("delete from User where id = ?1")
     //void deleteByUserId(Long id);
     //
     //@Transactional(timeout = 10)
     //@Query("select u from User u where u.emailAddress = ?1")
     //User findByEmailAddress(String emailAddress);


     @Query(value = "select * from UserDTO u where u.id in (:spIds)", nativeQuery = true)
     List<UserDTO> findById(@Param("spIds") List<Integer> spIds);
}
