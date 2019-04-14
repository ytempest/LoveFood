package com.ytempest.lovefood.http.data;

/**
 * @author ytempest
 *         Description：
 */
public class BaseActivityInfo {
    private Long actId;
    private String actImageUrl;
    private String actTitle;
    private String actDesc;
    private Long actStartTime;
    private Long actFinishTime;
    private String actRequest;

    public BaseActivityInfo() {
    }

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public String getActImageUrl() {
        return actImageUrl;
    }

    public void setActImageUrl(String actImageUrl) {
        this.actImageUrl = actImageUrl;
    }

    public String getActTitle() {
        return actTitle;
    }

    public void setActTitle(String actTitle) {
        this.actTitle = actTitle;
    }

    public String getActDesc() {
        return actDesc;
    }

    public void setActDesc(String actDesc) {
        this.actDesc = actDesc;
    }

    public Long getActStartTime() {
        return actStartTime;
    }

    public void setActStartTime(Long actStartTime) {
        this.actStartTime = actStartTime;
    }

    public Long getActFinishTime() {
        return actFinishTime;
    }

    public void setActFinishTime(Long actFinishTime) {
        this.actFinishTime = actFinishTime;
    }

    public String getActRequest() {
        return actRequest;
    }

    public void setActRequest(String actRequest) {
        this.actRequest = actRequest;
    }

    @Override
    public String toString() {
        return "BaseActivityInfo{" +
                "actId=" + actId +
                ", actImageUrl='" + actImageUrl + '\'' +
                ", actTitle='" + actTitle + '\'' +
                ", actDesc='" + actDesc + '\'' +
                ", actStartTime=" + actStartTime +
                ", actFinishTime=" + actFinishTime +
                ", actRequest='" + actRequest + '\'' +
                '}';
    }
}
