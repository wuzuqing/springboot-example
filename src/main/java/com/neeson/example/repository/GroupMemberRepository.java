package com.neeson.example.repository;


import com.neeson.example.entity.GroupMemberDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMemberDto, Integer> {

    GroupMemberDto findByUserIdAndGroupId(Integer userId, Integer groupId);
    List<GroupMemberDto> findByUserId (Integer userId);
    List<GroupMemberDto> findByGroupId (Integer groupId);
}