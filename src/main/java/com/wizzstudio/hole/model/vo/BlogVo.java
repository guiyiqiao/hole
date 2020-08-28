package com.wizzstudio.hole.model.vo;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.io.Serializable;

/**
 * @Author 桂乙侨
 * @Date 2020/8/26 18:33
 * @Version 1.0
 */
public class BlogVo implements Serializable {
    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "内容不能为空")
    private String content;

    //是否可评论，默认可评论

    private Boolean evaluable = true;

    //是否公开，默认私密
    private Boolean state = false;

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

    @Override
    public String toString() {
        return "BlogVo{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", evaluable=" + evaluable +
                ", state=" + state +
                '}';
    }
}
