package com.coo8.topic.expenditure.pojo;

import java.math.BigDecimal;

public class ExpendAnalyseCharge {
    private String yearNumber;

    private String monthNumber;

    private Integer channelId;

    private String stairName;

    private BigDecimal monthCost;

    public ExpendAnalyseCharge() {
    }

    public ExpendAnalyseCharge(String yearNumber, String monthNumber, Integer channelId, String stairName, BigDecimal monthCost) {

        this.yearNumber = yearNumber;
        this.monthNumber = monthNumber;
        this.channelId = channelId;
        this.stairName = stairName;
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

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getStairName() {
        return stairName;
    }

    public void setStairName(String stairName) {
        this.stairName = stairName;
    }

    public BigDecimal getMonthCost() {
        return monthCost;
    }

    public void setMonthCost(BigDecimal monthCost) {
        this.monthCost = monthCost;
    }

    @Override
    public String toString() {
        return "ExpendAnalyseCharge{" +
                "yearNumber='" + yearNumber + '\'' +
                ", monthNumber='" + monthNumber + '\'' +
                ", channelId=" + channelId +
                ", stairName='" + stairName + '\'' +
                ", monthCost=" + monthCost +
                '}';
    }
}
