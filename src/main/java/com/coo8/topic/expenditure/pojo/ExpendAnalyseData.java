package com.coo8.topic.expenditure.pojo;

import java.math.BigDecimal;

public class ExpendAnalyseData {
    private String portName;
    private String firstChannel;
    private BigDecimal expendCount;

    public ExpendAnalyseData() {
    }

    public ExpendAnalyseData(String portName, String firstChannel, BigDecimal expendCount) {
        this.portName = portName;
        this.firstChannel = firstChannel;
        this.expendCount = expendCount;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getFirstChannel() {
        return firstChannel;
    }

    public void setFirstChannel(String firstChannel) {
        this.firstChannel = firstChannel;
    }

    public BigDecimal getExpendCount() {
        return expendCount;
    }

    public void setExpendCount(BigDecimal expendCount) {
        this.expendCount = expendCount;
    }

    @Override
    public String toString() {
        return "ExpendAnalyseData{" +
                "portName='" + portName + '\'' +
                ", firstChannel='" + firstChannel + '\'' +
                ", expendCount=" + expendCount +
                '}';
    }
}
