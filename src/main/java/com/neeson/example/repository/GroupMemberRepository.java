package com.neeson.example.repository;


import com.neeson.example.entity.GroupMemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMemberDto, Integer> {

    GroupMemberDto findByUserIdAndGroupId(Integer userId, Integer groupId);


    List<GroupMemberDto> findByGroupId(Integer groupId);

    List<GroupMemberDto> findByUserId(Integer userId);
    //@Transactional(timeout = 10)
//    @Query("select u from UserDto u where u.emailAddress = ?1")

    @Query(value = "select gm.group1_id from group_member gm where gm.user_id = ?1", nativeQuery = true)
    List<Integer> findGroupIdByUserId(Integer userId);
}