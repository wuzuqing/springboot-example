package com.neeson.example.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@Data
@Table(name = "dlt_cp")
public class DltCpDto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer qiNo;
    private String date;

    //前5个号码
    private int blueNum1;
    private int blueNum2;
    private int blueNum3;
    private int blueNum4;
    private int blueNum5;
    private int redNum1;
    private int redNum2;

}
