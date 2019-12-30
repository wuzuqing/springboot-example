package com.neeson.example.repository;


import com.neeson.example.entity.GroupDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupDto, Integer> {



    @Query(value = "select * from group1 u where u.id in ( select group1_id from group_member where user_id = :userId )", nativeQuery = true)
    List<GroupDto> findByUserId(@Param("userId") Integer userId);
}