package com.neeson.example.repository;

import com.neeson.example.entity.ChatMsgDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMsgRepository extends JpaRepository<ChatMsgDto, Integer> {

}