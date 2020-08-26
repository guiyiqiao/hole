package com.wizzstudio.hole.model;

import io.swagger.models.auth.In;

import java.io.Serializable;

/**
 * 此类用作用户对Blog心事的举报内容信息表
 * @Author 桂乙侨
 * @Date 2020/8/26 22:25
 * @Version 1.0
 */
public class BlogReport implements Serializable {
    private Integer id;

    private Integer blogId;

    //举报其他详情
    private String content;

    private Integer userId;


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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BlogReport{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                '}';
    }


    public static final class BlogReportBuilder {
        private Integer id;
        private Integer blogId;
        //举报其他详情
        private String content;
        private Integer userId;

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

        public BlogReportBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public BlogReportBuilder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public BlogReport build() {
            BlogReport blogReport = new BlogReport();
            blogReport.setId(id);
            blogReport.setBlogId(blogId);
            blogReport.setContent(content);
            blogReport.setUserId(userId);
            return blogReport;
        }
    }
}
