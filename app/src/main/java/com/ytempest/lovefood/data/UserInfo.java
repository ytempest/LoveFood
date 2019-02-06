package com.ytempest.lovefood.data;

/**
 * @author ytempest
 *         Description：
 */
public class UserInfo {
    private Long userId;
    private String userAccount;
    private String userHeadUrl;
    private String userSex;
    private Long userBirth;
    private String userPhone;
    private String userEmail;
    private String userQQ;

    public UserInfo() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Long getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Long userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserQQ() {
        return userQQ;
    }

    public void setUserQQ(String userQQ) {
        this.userQQ = userQQ;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userAccount='" + userAccount + '\'' +
                ", userHeadUrl='" + userHeadUrl + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBirth=" + userBirth +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userQQ='" + userQQ + '\'' +
                '}';
    }
}
