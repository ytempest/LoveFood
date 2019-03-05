package com.ytempest.lovefood.http.data;

/**
 * @author ytempest
 *         Description：
 */
public class ActivityInfo {

    public static String getTest() {
        return "{\"code\":1,\"msg\":\"获取成功\",\"data\":{\"list\":[{\"actId\":1,\"actImageUrl\":\"1\",\"actTitle\":\"1\",\"actDesc\":\"1\",\"actStartTime\":1546338034000,\"actFinishTime\":1550830840000,\"actRequest\":\"1\"},{\"actId\":2,\"actImageUrl\":\"2\",\"actTitle\":\"2\",\"actDesc\":\"2\",\"actStartTime\":null,\"actFinishTime\":null,\"actRequest\":\"2\"},{\"actId\":3,\"actImageUrl\":\"3\",\"actTitle\":\"3\",\"actDesc\":\"3\",\"actStartTime\":1549621253000,\"actFinishTime\":1546942857000,\"actRequest\":\"3\"}],\"total\":3,\"pageSize\":10,\"currentPage\":1,\"pageCount\":1}}";
    }

    private Long actId;
    private String actImageUrl;
    private String actTitle;
    private String actDesc;
    private Long actStartTime;
    private Long actFinishTime;
    private String actRequest;

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
        return "ActivityInfo{" +
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
