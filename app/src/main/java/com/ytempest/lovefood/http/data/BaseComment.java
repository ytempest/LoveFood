package com.ytempest.lovefood.http.data;

/**
 * @author ytempest
 *         Description：
 */
public class BaseComment {
    private Long commentId;
    private Long topicId;
    private String commentContent;
    private Long commentTime;
    private Long commentFromUser;
    private Long commentToUser;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Long commentTime) {
        this.commentTime = commentTime;
    }

    public Long getCommentFromUser() {
        return commentFromUser;
    }

    public void setCommentFromUser(Long commentFromUser) {
        this.commentFromUser = commentFromUser;
    }

    public Long getCommentToUser() {
        return commentToUser;
    }

    public void setCommentToUser(Long commentToUser) {
        this.commentToUser = commentToUser;
    }
}
