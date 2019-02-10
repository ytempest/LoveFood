package com.ytempest.lovefood.http.data;

import java.util.List;

/**
 * @author ytempest
 *         Description：
 */
public class CookbookInfo {
    private Long cookId;
    private String cookGroup;
    private String cookType;
    private String cookImageUrl;
    private Long cookUserId;
    private String cookTitle;
    private String cookDesc;
    private Long cookPublishTime;
    private String userHeadUrl;
    private String userAccount;
    private Long collectCount;
    private List<MainListBean> mainList;
    private List<AccListBean> accList;
    private List<ProceListBean> proceList;

    public CookbookInfo() {
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

    public Long getCookUserId() {
        return cookUserId;
    }

    public void setCookUserId(Long cookUserId) {
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

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Long getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Long collectCount) {
        this.collectCount = collectCount;
    }

    public List<MainListBean> getMainList() {
        return mainList;
    }

    public void setMainList(List<MainListBean> mainList) {
        this.mainList = mainList;
    }

    public List<AccListBean> getAccList() {
        return accList;
    }

    public void setAccList(List<AccListBean> accList) {
        this.accList = accList;
    }

    public List<ProceListBean> getProceList() {
        return proceList;
    }

    public void setProceList(List<ProceListBean> proceList) {
        this.proceList = proceList;
    }

    public static class MainListBean {
        private Long mainId;
        private Long cookId;
        private String mainName;
        private String mainAmount;

        public MainListBean() {
        }

        public Long getMainId() {
            return mainId;
        }

        public void setMainId(Long mainId) {
            this.mainId = mainId;
        }

        public Long getCookId() {
            return cookId;
        }

        public void setCookId(Long cookId) {
            this.cookId = cookId;
        }

        public String getMainName() {
            return mainName;
        }

        public void setMainName(String mainName) {
            this.mainName = mainName;
        }

        public String getMainAmount() {
            return mainAmount;
        }

        public void setMainAmount(String mainAmount) {
            this.mainAmount = mainAmount;
        }

        @Override
        public String toString() {
            return "MainListBean{" +
                    "mainId=" + mainId +
                    ", cookId=" + cookId +
                    ", mainName='" + mainName + '\'' +
                    ", mainAmount='" + mainAmount + '\'' +
                    '}';
        }
    }

    public static class AccListBean {
        private Long accId;
        private Long cookId;
        private String accName;
        private String accAmount;

        public AccListBean() {
        }

        public Long getAccId() {
            return accId;
        }

        public void setAccId(Long accId) {
            this.accId = accId;
        }

        public Long getCookId() {
            return cookId;
        }

        public void setCookId(Long cookId) {
            this.cookId = cookId;
        }

        public String getAccName() {
            return accName;
        }

        public void setAccName(String accName) {
            this.accName = accName;
        }

        public String getAccAmount() {
            return accAmount;
        }

        public void setAccAmount(String accAmount) {
            this.accAmount = accAmount;
        }

        @Override
        public String toString() {
            return "AccListBean{" +
                    "accId=" + accId +
                    ", cookId=" + cookId +
                    ", accName='" + accName + '\'' +
                    ", accAmount='" + accAmount + '\'' +
                    '}';
        }
    }

    public static class ProceListBean {
        private Long proceId;
        private Long cookId;
        private Integer proceNo;
        private String proceImageUrl;
        private String proceDesc;

        public ProceListBean() {
        }

        public Long getProceId() {
            return proceId;
        }

        public void setProceId(Long proceId) {
            this.proceId = proceId;
        }

        public Long getCookId() {
            return cookId;
        }

        public void setCookId(Long cookId) {
            this.cookId = cookId;
        }

        public Integer getProceNo() {
            return proceNo;
        }

        public void setProceNo(Integer proceNo) {
            this.proceNo = proceNo;
        }

        public String getProceImageUrl() {
            return proceImageUrl;
        }

        public void setProceImageUrl(String proceImageUrl) {
            this.proceImageUrl = proceImageUrl;
        }

        public String getProceDesc() {
            return proceDesc;
        }

        public void setProceDesc(String proceDesc) {
            this.proceDesc = proceDesc;
        }

        @Override
        public String toString() {
            return "ProceListBean{" +
                    "proceId=" + proceId +
                    ", cookId=" + cookId +
                    ", proceNo=" + proceNo +
                    ", proceImageUrl='" + proceImageUrl + '\'' +
                    ", proceDesc='" + proceDesc + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CookbookInfo{" +
                "cookId=" + cookId +
                ", cookGroup='" + cookGroup + '\'' +
                ", cookType='" + cookType + '\'' +
                ", cookImageUrl='" + cookImageUrl + '\'' +
                ", cookUserId=" + cookUserId +
                ", cookTitle='" + cookTitle + '\'' +
                ", cookDesc='" + cookDesc + '\'' +
                ", cookPublishTime=" + cookPublishTime +
                ", userHeadUrl='" + userHeadUrl + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", collectCount=" + collectCount +
                ", mainList=" + mainList +
                ", accList=" + accList +
                ", proceList=" + proceList +
                '}';
    }
}