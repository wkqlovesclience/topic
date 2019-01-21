package com.coo8.topic.expenditure.pojo;

import java.math.BigDecimal;

public class DataContains {
    private long stairId;
    private String stairName;
    private BigDecimal expendAmount;

    public DataContains() {
    }


    public DataContains(long stairId, String stairName, BigDecimal expendAmount) {
        this.stairId = stairId;
        this.stairName = stairName;
        this.expendAmount = expendAmount;
    }

    public long getStairId() {
        return stairId;
    }

    public void setStairId(long stairId) {
        this.stairId = stairId;
    }

    public String getStairName() {
        return stairName;
    }

    public void setStairName(String stairName) {
        this.stairName = stairName;
    }

    public BigDecimal getExpendAmount() {
        return expendAmount;
    }

    public void setExpendAmount(BigDecimal expendAmount) {
        this.expendAmount = expendAmount;
    }

    @Override
    public String toString() {
        return "DataContains{" +
                "stairId=" + stairId +
                ", stairName='" + stairName + '\'' +
                ", expendAmount=" + expendAmount +
                '}';
    }
}
