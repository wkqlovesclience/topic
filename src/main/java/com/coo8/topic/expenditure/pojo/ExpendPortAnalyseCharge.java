package com.coo8.topic.expenditure.pojo;

import java.math.BigDecimal;

public class ExpendPortAnalyseCharge {
    private String yearNumber;

    private String monthNumber;

    private Integer portId;

    private String portName;

    private BigDecimal monthCost;

    public ExpendPortAnalyseCharge() {
    }

    public ExpendPortAnalyseCharge(String yearNumber, String monthNumber, Integer portId, String portName, BigDecimal monthCost) {
        this.yearNumber = yearNumber;
        this.monthNumber = monthNumber;
        this.portId = portId;
        this.portName = portName;
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

    public Integer getPortId() {
        return portId;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public BigDecimal getMonthCost() {
        return monthCost;
    }

    public void setMonthCost(BigDecimal monthCost) {
        this.monthCost = monthCost;
    }

    @Override
    public String toString() {
        return "ExpendPortAnalyseCharge{" +
                "yearNumber='" + yearNumber + '\'' +
                ", monthNumber='" + monthNumber + '\'' +
                ", portId=" + portId +
                ", portName='" + portName + '\'' +
                ", monthCost=" + monthCost +
                '}';
    }
}
