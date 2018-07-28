package com.neeson.example.repository;

import com.neeson.example.entity.ChatMsgDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMsgRepository extends JpaRepository<ChatMsgDto, Long> {


    //    @Query(value = "select  * from chat_msg  c where c.create_time > :ct and ( c.to_id = :toId or ( c.group_id in ( select  g.group_id from group_member g  where  g.user_id = ?2 )))", nativeQuery = true)
    @Query(value = "select DISTINCT u from ChatMsgDto u where u.createTime > ?1 and u.chatType = 2 and ( u.to = ?2 or u.from = ?2)")
    List<ChatMsgDto> findFriendOffMsgList(Long ct, Integer toId);

    @Query(value = "select DISTINCT u from ChatMsgDto u where u.createTime > ?1 and u.chatType = 1 and u.group_id > 0 ")
    List<ChatMsgDto> findGroupOffMsgList(Long ct);
}