package com.neeson.example.book.cp;

import java.util.List;

public class SsqCpResult extends CpResult {
    private List<Integer> redNumbers;
    private List<Integer> blueNumbers;

    public List<Integer> getRedNumbers() {
        return redNumbers;
    }

    public void setRedNumbers(List<Integer> redNumbers) {
        this.redNumbers = redNumbers;
    }

    public List<Integer> getBlueNumbers() {
        return blueNumbers;
    }

    public void setBlueNumbers(List<Integer> blueNumbers) {
        this.blueNumbers = blueNumbers;
    }
}
