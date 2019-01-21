package com.coo8.topic.expenditure.pojo;

import java.math.BigDecimal;

public class WholeExpendAnalyseCharge {
    private String yearNumber;

    private String monthNumber;

    private BigDecimal monthCost;

    public WholeExpendAnalyseCharge() {
    }

    public WholeExpendAnalyseCharge(String yearNumber, String monthNumber, BigDecimal monthCost) {
        this.yearNumber = yearNumber;
        this.monthNumber = monthNumber;
        this.monthCost = monthCost;
    }

    public String getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(String yearNumber) {
        this.yearNumber = yearNumber;
    }

    public String getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(String monthNumber) {
        this.monthNumber = monthNumber;
    }

    public BigDecimal getMonthCost() {
        return monthCost;
    }

    public void setMonthCost(BigDecimal monthCost) {
        this.monthCost = monthCost;
    }

    @Override
    public String toString() {
        return "WholeExpendAnalyseCharge{" +
                "yearNumber='" + yearNumber + '\'' +
                ", monthNumber='" + monthNumber + '\'' +
                ", monthCost=" + monthCost +
                '}';
    }
}
