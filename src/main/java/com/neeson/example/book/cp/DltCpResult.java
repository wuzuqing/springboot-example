package com.neeson.example.book.cp;


public class DltCpResult extends CpResult {
    //前5个号码
    private int blueNum1;
    private int blueNum2;
    private int blueNum3;
    private int blueNum4;
    private int blueNum5;
    private int redNum1;
    private int redNum2;

    public int getBlueNum1() {
        return blueNum1;
    }

    public void setBlueNum1(int blueNum1) {
        this.blueNum1 = blueNum1;
    }

    public int getBlueNum2() {
        return blueNum2;
    }

    public void setBlueNum2(int blueNum2) {
        this.blueNum2 = blueNum2;
    }

    public int getBlueNum3() {
        return blueNum3;
    }

    public void setBlueNum3(int blueNum3) {
        this.blueNum3 = blueNum3;
    }

    public int getBlueNum4() {
        return blueNum4;
    }

    public void setBlueNum4(int blueNum4) {
        this.blueNum4 = blueNum4;
    }

    public int getBlueNum5() {
        return blueNum5;
    }

    public void setBlueNum5(int blueNum5) {
        this.blueNum5 = blueNum5;
    }

    public int getRedNum1() {
        return redNum1;
    }

    public void setRedNum1(int redNum1) {
        this.redNum1 = redNum1;
    }

    public int getRedNum2() {
        return redNum2;
    }

    public void setRedNum2(int redNum2) {
        this.redNum2 = redNum2;
    }

    @Override
    public String toString() {
        return "DltCpResult{" +
                "qiNo='" + getQiNo() + '\'' +
                ", date='" + getDate() + '\'' +
                ", blueNum1=" + blueNum1 +
                ", blueNum2=" + blueNum2 +
                ", blueNum3=" + blueNum3 +
                ", blueNum4=" + blueNum4 +
                ", blueNum5=" + blueNum5 +
                ", redNum1=" + redNum1 +
                ", redNum2=" + redNum2 +
                '}';
    }
}
