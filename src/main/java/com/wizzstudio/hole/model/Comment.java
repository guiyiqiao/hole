package com.wizzstudio.hole.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 11:26
 * @Version 1.0
 */
public class Comment implements Serializable ,TimelineComparable{
    private Integer id;

    private Integer userId;

    private Integer blogId;

    private String content;

    private Integer thank;

    //是否为私信，默认false
    private Boolean overt;

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
                ", thank=" + thank +
                ", overt=" + overt +
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

    public Integer getThank() {
        return thank;
    }

    public void setThank(Integer thank) {
        this.thank = thank;
    }

    public Boolean getOvert() {
        return overt;
    }

    public void setOvert(Boolean overt) {
        this.overt = overt;
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

    @Override
    public Date getDate() {
        return null;
    }

    public static final class CommentBuilder {
        private Integer id;
        private Integer userId;
        private Integer blogId;
        private String content;
        private Integer thank;
        //是否为私信，默认false
        private Boolean overt;
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

        public CommentBuilder withThank(Integer thank) {
            this.thank = thank;
            return this;
        }

        public CommentBuilder withOvert(Boolean overt) {
            this.overt = overt;
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
            comment.setThank(thank);
            comment.setOvert(overt);
            comment.setReport(report);
            comment.setPublishTime(publishTime);
            comment.setValid(valid);
            return comment;
        }
    }
}
