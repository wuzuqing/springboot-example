package com.neeson.example.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "chat_msg")
public class ChatMsgDto implements Comparable<ChatMsgDto> {

    @Id
    @Column( length = 32,nullable = false)
    private String id;
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
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "local_path")
    private String localPath;

    @Override
    public int compareTo(ChatMsgDto o) {
        return (int) (createTime - o.createTime);
    }
}
