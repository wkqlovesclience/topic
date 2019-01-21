package com.coo8.topic.expenditure.pojo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ExpendAnalyseBase {

    private int portId;
    private String portName;
    private List<DataContains> stairChannelAndExpends;
    private BigDecimal portCostAmount;
    public ExpendAnalyseBase() {
    }

    public ExpendAnalyseBase(int portId, String portName, List<DataContains> stairChannelAndExpends, BigDecimal portCostAmount) {
        this.portId = portId;
        this.portName = portName;
        this.stairChannelAndExpends = stairChannelAndExpends;
        this.portCostAmount = portCostAmount;
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public List<DataContains> getStairChannelAndExpends() {
        return stairChannelAndExpends;
    }

    public void setStairChannelAndExpends(List<DataContains> stairChannelAndExpends) {
        this.stairChannelAndExpends = stairChannelAndExpends;
    }

    public BigDecimal getPortCostAmount() {
        return portCostAmount;
    }

    public void setPortCostAmount(BigDecimal portCostAmount) {
        this.portCostAmount = portCostAmount;
    }

    @Override
    public String toString() {
        return "ExpendAnalyseBase{" +
                "portId=" + portId +
                ", portName='" + portName + '\'' +
                ", stairChannelAndExpends=" + stairChannelAndExpends +
                ", portCostAmount=" + portCostAmount +
                '}';
    }
}
