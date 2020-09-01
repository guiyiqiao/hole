package com.wizzstudio.hole.model;

import javax.persistence.Id;
import java.util.Date;

/**
 * @Author 桂乙侨
 * @Date 2020/8/29 15:14
 * @Version 1.0
 */
public class Comment {
    @Id
    private Integer id;

    private Integer userId;

    private Integer blogId;

    private String content;

    //是否被举报
    private Boolean report;

    private Date publishTime;

    private Boolean valid;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", blogId=" + blogId +
                ", content='" + content + '\'' +
                ", report=" + report +
                ", publishTime=" + publishTime +
                ", valid=" + valid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public static final class CommentBuilder {
        private Integer id;
        private Integer userId;
        private Integer blogId;
        private String content;
        //是否被举报
        private Boolean report;
        private Date publishTime;
        private Boolean valid;

        private CommentBuilder() {
        }

        public static CommentBuilder aComment() {
            return new CommentBuilder();
        }

        public CommentBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public CommentBuilder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public CommentBuilder withBlogId(Integer blogId) {
            this.blogId = blogId;
            return this;
        }

        public CommentBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public CommentBuilder withReport(Boolean report) {
            this.report = report;
            return this;
        }

        public CommentBuilder withPublishTime(Date publishTime) {
            this.publishTime = publishTime;
            return this;
        }

        public CommentBuilder withValid(Boolean valid) {
            this.valid = valid;
            return this;
        }

        public Comment build() {
            Comment comment = new Comment();
            comment.setId(id);
            comment.setUserId(userId);
            comment.setBlogId(blogId);
            comment.setContent(content);
            comment.setReport(report);
            comment.setPublishTime(publishTime);
            comment.setValid(valid);
            return comment;
        }
    }
}
