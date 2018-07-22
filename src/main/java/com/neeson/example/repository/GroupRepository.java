package com.neeson.example.repository;


import com.neeson.example.entity.GroupDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupDto, Integer> {


    @Query(value = "select * from group_dto u where u.id in (:spIds)", nativeQuery = true)
    List<GroupDto> findById(@Param("spIds") List<Integer> spIds);
}