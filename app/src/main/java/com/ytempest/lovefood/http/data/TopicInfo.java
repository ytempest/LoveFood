package com.ytempest.lovefood.http.data;

import java.util.List;

/**
 * @author ytempest
 *         Description：
 */
public class TopicInfo {
    private Long topicId;
    private Long userId;
    private String userHeadUrl;
    private String userAccount;
    private String topicTitle;
    private String topicContent;
    private Long topicPublishTime;
    private Long commentCount;
    private Long zanCount;
    private List<Image> topicImage;

    public TopicInfo() {
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public long getTopicPublishTime() {
        return topicPublishTime;
    }

    public void setTopicPublishTime(Long topicPublishTime) {
        this.topicPublishTime = topicPublishTime;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getZanCount() {
        return zanCount;
    }

    public void setZanCount(Long zanCount) {
        this.zanCount = zanCount;
    }

    public List<Image> getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(List<Image> topicImage) {
        this.topicImage = topicImage;
    }

    public static class Image {
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
