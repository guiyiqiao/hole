package com.wizzstudio.hole.model.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 此类用作添加回声时的参数接收类
 * @Author 桂乙侨
 * @Date 2020/8/27 12:03
 * @Version 1.0
 */
public class CommentVo {

    @NotNull
    private Integer blogId;
    @NotBlank
    private String content;

    //是否为私信，默认false
    private Boolean state = false;


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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CommentVo{" +
                "blogId=" + blogId +
                ", content='" + content + '\'' +
                ", state=" + state +
                '}';
    }

}
