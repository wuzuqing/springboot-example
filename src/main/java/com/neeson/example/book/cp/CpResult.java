package com.neeson.example.book.cp;

import java.io.Serializable;

public class CpResult implements Serializable {
    private String qiNo;
    private String date;

    public String getQiNo() {
        return qiNo;
    }

    public void setQiNo(String qiNo) {
        this.qiNo = qiNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CpResult{" +
                "qiNo='" + qiNo + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
