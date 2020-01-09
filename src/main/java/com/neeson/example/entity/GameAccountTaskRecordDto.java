package com.neeson.example.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Entity
@Data
@Table(name = "game_account_task_record")
public class GameAccountTaskRecordDto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String account;        //用户名
    private String pwd;
    private String area;
    //完成任务 json字符串
    private String finishTask;

    /**
     * 格式 yyyy-MM-dd
     */
    private String finishData;

}
