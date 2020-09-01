package com.wizzstudio.hole.model;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author 桂乙侨
 * @Date 2020/8/27 18:39
 * @Version 1.0
 */
public class CommentReport implements Serializable {
    @Id
    private Integer id;

    private Integer commentId;

    //举报原因
    private String reason;

    private Integer userId;

    //是否已经被处理
    private Boolean solved;

    @Override
    public String toString() {
        return "CommentReport{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", reason='" + reason + '\'' +
                ", userId=" + userId +
                ", solved=" + solved +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    public static final class CommentReportBuilder {
        private Integer id;
        private Integer commentId;
        //举报原因
        private String reason;
        private Integer userId;
        //是否已经被处理
        private Boolean solved;

        private CommentReportBuilder() {
        }

        public static CommentReportBuilder aCommentReport() {
            return new CommentReportBuilder();
        }

        public CommentReportBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public CommentReportBuilder withCommentId(Integer commentId) {
            this.commentId = commentId;
            return this;
        }

        public CommentReportBuilder withReason(String reason) {
            this.reason = reason;
            return this;
        }

        public CommentReportBuilder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public CommentReportBuilder withSolved(Boolean solved) {
            this.solved = solved;
            return this;
        }

        public CommentReport build() {
            CommentReport commentReport = new CommentReport();
            commentReport.setId(id);
            commentReport.setCommentId(commentId);
            commentReport.setReason(reason);
            commentReport.setUserId(userId);
            commentReport.setSolved(solved);
            return commentReport;
        }
    }
}
