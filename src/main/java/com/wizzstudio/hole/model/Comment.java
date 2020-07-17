package com.wizzstudio.hole.model;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 11:26
 * @Version 1.0
 */
public class Comment {
    private Integer id;

    private Integer userId;

    private Integer blogId;

    private String content;

    private Integer thank;

    //是否为私信，默认false
    private Boolean state;

    //是否被举报
    private Boolean tipOff;

    private Boolean valid;






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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getTipOff() {
        return tipOff;
    }

    public void setTipOff(Boolean tipOff) {
        this.tipOff = tipOff;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", blogId=" + blogId +
                ", content='" + content + '\'' +
                ", thank=" + thank +
                ", state=" + state +
                ", tipOff=" + tipOff +
                ", valid=" + valid +
                '}';
    }

    public static final class CommentBuilder {
        private Integer id;
        private Integer userId;
        private Integer blogId;
        private String content;
        private Integer thank;
        private Boolean state;
        private Boolean tipOff;
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

        public CommentBuilder withState(Boolean state) {
            this.state = state;
            return this;
        }

        public CommentBuilder withTipOff(Boolean tipOff) {
            this.tipOff = tipOff;
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
            comment.setState(state);
            comment.setTipOff(tipOff);
            comment.setValid(valid);
            return comment;
        }
    }
}
