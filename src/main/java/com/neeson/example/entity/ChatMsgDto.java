package com.neeson.example.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "chat_msg")
public class ChatMsgDto implements Comparable<ChatMsgDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer chatType;
    private String content;
    private Long createTime;
    @Column(name = "from_id")
    private Integer from;
    @Column(name = "group_id")
    private Integer group_id;
    private Integer msgType;
    @Column(name = "to_id")
    private Integer to;
    private String url;  //图片-视频 -语音 路径
    @Column(name = "session_id")
    private String sessionId;
    @Override
    public int compareTo(ChatMsgDto o) {
        return (int) (createTime - o.createTime);
    }
}
