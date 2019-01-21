package com.coo8.topic.model;

public class TitleInvalidData {
    private String createTime;
    private Integer count;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TitleInvalidData{" +
                "createTime='" + createTime + '\'' +
                ", count=" + count +
                '}';
    }
}
