package com.neeson.example.entity;


import com.neeson.example.util.NumberToWuxing;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@Data
@Table(name = "dlt_cp_extend")
public class DltCpExtendDto implements Serializable {

    public DltCpExtendDto() {
    }

    public DltCpExtendDto(DltCpDto cpDto) {
        setQiNo(cpDto.getQiNo());
        setBlueNum1(NumberToWuxing.getWxByNumber(cpDto.getBlueNum1()));
        setBlueNum2(NumberToWuxing.getWxByNumber(cpDto.getBlueNum2()));
        setBlueNum3(NumberToWuxing.getWxByNumber(cpDto.getBlueNum3()));
        setBlueNum4(NumberToWuxing.getWxByNumber(cpDto.getBlueNum4()));
        setBlueNum5(NumberToWuxing.getWxByNumber(cpDto.getBlueNum5()));
        setRedNum1(NumberToWuxing.getWxByNumber(cpDto.getRedNum1()));
        setRedNum2(NumberToWuxing.getWxByNumber(cpDto.getRedNum2()));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer qiNo;
    //前5个号码
    private String blueNum1;
    private String blueNum2;
    private String blueNum3;
    private String blueNum4;
    private String blueNum5;
    private String redNum1;
    private String redNum2;

}
