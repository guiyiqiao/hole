package com.wizzstudio.hole.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 11:26
 * @Version 1.0
 */
public class Echo implements Serializable ,TimelineComparable{
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

    //是否已被收到
    private Boolean solved;

    private Boolean valid;

    @Override
    public String toString() {
        return "Echo{" +
                "id=" + id +
                ", userId=" + userId +
                ", blogId=" + blogId +
                ", content='" + content + '\'' +
                ", thank=" + thank +
                ", overt=" + overt +
                ", report=" + report +
                ", publishTime=" + publishTime +
                ", solved=" + solved +
                ", valid=" + valid +
                '}';
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
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


    public static final class EchoBuilder {
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
        //是否已被收到
        private Boolean solved;
        private Boolean valid;

        private EchoBuilder() {
        }

        public static EchoBuilder anEcho() {
            return new EchoBuilder();
        }

        public EchoBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public EchoBuilder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public EchoBuilder withBlogId(Integer blogId) {
            this.blogId = blogId;
            return this;
        }

        public EchoBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public EchoBuilder withThank(Integer thank) {
            this.thank = thank;
            return this;
        }

        public EchoBuilder withOvert(Boolean overt) {
            this.overt = overt;
            return this;
        }

        public EchoBuilder withReport(Boolean report) {
            this.report = report;
            return this;
        }

        public EchoBuilder withPublishTime(Date publishTime) {
            this.publishTime = publishTime;
            return this;
        }

        public EchoBuilder withSolved(Boolean solved) {
            this.solved = solved;
            return this;
        }

        public EchoBuilder withValid(Boolean valid) {
            this.valid = valid;
            return this;
        }

        public Echo build() {
            Echo echo = new Echo();
            echo.setId(id);
            echo.setUserId(userId);
            echo.setBlogId(blogId);
            echo.setContent(content);
            echo.setThank(thank);
            echo.setOvert(overt);
            echo.setReport(report);
            echo.setPublishTime(publishTime);
            echo.setSolved(solved);
            echo.setValid(valid);
            return echo;
        }
    }
}
