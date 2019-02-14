package com.ytempest.lovefood.http.data;

/**
 * @author ytempest
 *         Description：
 */
public class BaseCookbook {
    private Long cookId;
    private String cookGroup;
    private String cookType;
    private String cookImageUrl;
    private Integer cookUserId;
    private String cookTitle;
    private String cookDesc;
    private Long cookPublishTime;

    public BaseCookbook() {
    }

    public Long getCookId() {
        return cookId;
    }

    public void setCookId(Long cookId) {
        this.cookId = cookId;
    }

    public String getCookGroup() {
        return cookGroup;
    }

    public void setCookGroup(String cookGroup) {
        this.cookGroup = cookGroup;
    }

    public String getCookType() {
        return cookType;
    }

    public void setCookType(String cookType) {
        this.cookType = cookType;
    }

    public String getCookImageUrl() {
        return cookImageUrl;
    }

    public void setCookImageUrl(String cookImageUrl) {
        this.cookImageUrl = cookImageUrl;
    }

    public Integer getCookUserId() {
        return cookUserId;
    }

    public void setCookUserId(Integer cookUserId) {
        this.cookUserId = cookUserId;
    }

    public String getCookTitle() {
        return cookTitle;
    }

    public void setCookTitle(String cookTitle) {
        this.cookTitle = cookTitle;
    }

    public String getCookDesc() {
        return cookDesc;
    }

    public void setCookDesc(String cookDesc) {
        this.cookDesc = cookDesc;
    }

    public Long getCookPublishTime() {
        return cookPublishTime;
    }

    public void setCookPublishTime(Long cookPublishTime) {
        this.cookPublishTime = cookPublishTime;
    }

    @Override
    public String toString() {
        return "BaseCookbook{" +
                "cookId=" + cookId +
                ", cookGroup='" + cookGroup + '\'' +
                ", cookType='" + cookType + '\'' +
                ", cookImageUrl='" + cookImageUrl + '\'' +
                ", cookUserId=" + cookUserId +
                ", cookTitle='" + cookTitle + '\'' +
                ", cookDesc='" + cookDesc + '\'' +
                ", cookPublishTime=" + cookPublishTime +
                '}';
    }
}
