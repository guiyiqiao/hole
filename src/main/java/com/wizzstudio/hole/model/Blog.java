package com.wizzstudio.hole.model;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 11:23
 * @Version 1.0
 */
public class Blog implements Serializable {
    @Id
    private Integer id;

    private Integer userId;

    private String title;

    private String content;

    //拥抱/点赞
    private Integer hug;

    //发布时间
    private Date publishTime;

    //是否可评论，默认可评论
    private Boolean evaluable;

    //是否公开，默认公开
    private Boolean overt;

    private Boolean valid;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", hug=" + hug +
                ", publishTime=" + publishTime +
                ", evaluable=" + evaluable +
                ", overt=" + overt +
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Boolean getEvaluable() {
        return evaluable;
    }

    public void setEvaluable(Boolean evaluable) {
        this.evaluable = evaluable;
    }

    public Boolean getOvert() {
        return overt;
    }

    public void setOvert(Boolean overt) {
        this.overt = overt;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }


    public static final class BlogBuilder {
        private Integer id;
        private Integer userId;
        private String title;
        private String content;
        //拥抱/点赞
        private Integer hug;
        //发布时间
        private Date publishTime;
        //是否可评论，默认可评论
        private Boolean evaluable;
        //是否公开，默认公开
        private Boolean overt;
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

        public BlogBuilder withPublishTime(Date publishTime) {
            this.publishTime = publishTime;
            return this;
        }

        public BlogBuilder withEvaluable(Boolean evaluable) {
            this.evaluable = evaluable;
            return this;
        }

        public BlogBuilder withOvert(Boolean overt) {
            this.overt = overt;
            return this;
        }

        public BlogBuilder withValid(Boolean valid) {
            this.valid = valid;
            return this;
        }

        public Blog build() {
            Blog blog = new Blog();
            blog.setId(id);
            blog.setUserId(userId);
            blog.setTitle(title);
            blog.setContent(content);
            blog.setHug(hug);
            blog.setPublishTime(publishTime);
            blog.setEvaluable(evaluable);
            blog.setOvert(overt);
            blog.setValid(valid);
            return blog;
        }
    }
}
