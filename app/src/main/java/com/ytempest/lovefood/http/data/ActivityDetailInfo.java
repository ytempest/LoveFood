package com.ytempest.lovefood.http.data;

import java.util.List;

/**
 * @author ytempest
 *         Description：
 */
public class ActivityDetailInfo {
    private Long actId;
    private String actImageUrl;
    private String actTitle;
    private String actDesc;
    private Long actStartTime;
    private Long actFinishTime;
    private String actRequest;
    private Long partakeCount;
    private List<PrizeBean> prizeList;

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

    public Long getPartakeCount() {
        return partakeCount;
    }

    public void setPartakeCount(Long partakeCount) {
        this.partakeCount = partakeCount;
    }

    public List<PrizeBean> getPrizeList() {
        return prizeList;
    }

    public void setPrizeList(List<PrizeBean> prizeList) {
        this.prizeList = prizeList;
    }

    public static class PrizeBean {
        private Long prizeId;
        private String prizeName;
        private String prizePrize;

        public Long getPrizeId() {
            return prizeId;
        }

        public void setPrizeId(Long prizeId) {
            this.prizeId = prizeId;
        }

        public String getPrizeName() {
            return prizeName;
        }

        public void setPrizeName(String prizeName) {
            this.prizeName = prizeName;
        }

        public String getPrizePrize() {
            return prizePrize;
        }

        public void setPrizePrize(String prizePrize) {
            this.prizePrize = prizePrize;
        }
    }
}
