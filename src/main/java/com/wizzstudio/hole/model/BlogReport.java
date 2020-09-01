package com.wizzstudio.hole.model;

import io.swagger.models.auth.In;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 此类用作用户对Blog心事的举报内容信息表
 * @Author 桂乙侨
 * @Date 2020/8/26 22:25
 * @Version 1.0
 */
public class BlogReport implements Serializable {
    @Id
    private Integer id;

    private Integer blogId;

    //举报其他详情
    private String reason;

    private Integer userId;
    //是否已经被处理
    private Boolean solved;

    @Override
    public String toString() {
        return "BlogReport{" +
                "id=" + id +
                ", blogId=" + blogId +
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

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
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

    public static final class BlogReportBuilder {
        private Integer id;
        private Integer blogId;
        //举报其他详情
        private String reason;
        private Integer userId;
        //是否已经被处理
        private Boolean solved;

        private BlogReportBuilder() {
        }

        public static BlogReportBuilder aBlogReport() {
            return new BlogReportBuilder();
        }

        public BlogReportBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public BlogReportBuilder withBlogId(Integer blogId) {
            this.blogId = blogId;
            return this;
        }

        public BlogReportBuilder withReason(String reason) {
            this.reason = reason;
            return this;
        }

        public BlogReportBuilder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public BlogReportBuilder withSolved(Boolean solved) {
            this.solved = solved;
            return this;
        }

        public BlogReport build() {
            BlogReport blogReport = new BlogReport();
            blogReport.setId(id);
            blogReport.setBlogId(blogId);
            blogReport.setReason(reason);
            blogReport.setUserId(userId);
            blogReport.setSolved(solved);
            return blogReport;
        }
    }
}
