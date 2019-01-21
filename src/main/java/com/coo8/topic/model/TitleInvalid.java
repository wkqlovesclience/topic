package com.coo8.topic.model;

public class TitleInvalid {
    private Integer id;
    private String url;
    private Integer titleId;
    private String titleName;
    private String createTime;

    public TitleInvalid() {
    }

    public TitleInvalid(Integer id, String url, Integer titleId, String titleName, String createTime) {
        this.id = id;
        this.url = url;
        this.titleId = titleId;
        this.titleName = titleName;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TitleInvalid{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", titleId=" + titleId +
                ", titleName='" + titleName + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
