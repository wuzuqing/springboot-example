package com.neeson.example.repository;

import com.neeson.example.entity.DltCpDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DltCpRepository extends JpaRepository<DltCpDto, Integer> {
    DltCpDto findFirstByOrderByQiNoDesc();
}