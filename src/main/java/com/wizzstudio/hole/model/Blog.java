package com.wizzstudio.hole.model;

import java.util.Date;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 11:23
 * @Version 1.0
 */
public class Blog {

    private Integer id;

    private Integer userId;

    private String title;

    private String content;

    //拥抱/点赞
    private Integer hug;

    //发布时间
    private Date releaseTime;

    //是否可评论，默认可评论
    private Boolean evaluable;

    //是否公开，默认公开
    private Boolean state;

    private Boolean valid;





    public static final class BlogBuilder {
        private Integer id;
        private Integer userId;
        private String title;
        private String content;
        private Integer hug;
        private Date releaseTime;
        private Boolean evaluable;
        private Boolean state;
        private Boolean valid;

        private BlogBuilder() {
        }

        public static BlogBuilder aBlog() {
            return new BlogBuilder();
        }

        public BlogBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public BlogBuilder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public BlogBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public BlogBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public BlogBuilder withHug(Integer hug) {
            this.hug = hug;
            return this;
        }

        public BlogBuilder withReleaseTime(Date releaseTime) {
            this.releaseTime = releaseTime;
            return this;
        }

        public BlogBuilder withEvaluable(Boolean evaluable) {
            this.evaluable = evaluable;
            return this;
        }

        public BlogBuilder withState(Boolean state) {
            this.state = state;
            return this;
        }

        public BlogBuilder withValid(Boolean valid) {
            this.valid = valid;
            return this;
        }

        public Blog build() {
            Blog blog = new Blog();
            blog.hug = this.hug;
            blog.content = this.content;
            blog.state = this.state;
            blog.title = this.title;
            blog.evaluable = this.evaluable;
            blog.userId = this.userId;
            blog.valid = this.valid;
            blog.releaseTime = this.releaseTime;
            blog.id = this.id;
            return blog;
        }
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", hug=" + hug +
                ", releaseTime=" + releaseTime +
                ", evaluable=" + evaluable +
                ", state=" + state +
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHug() {
        return hug;
    }

    public void setHug(Integer hug) {
        this.hug = hug;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Boolean getEvaluable() {
        return evaluable;
    }

    public void setEvaluable(Boolean evaluable) {
        this.evaluable = evaluable;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
