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
    @NotBlank(message = "请填写标题呦")
    private String title;

    @NotBlank(message = "还没有写下内容呦")
    private String content;

    //是否可评论，默认不可评论

    private Boolean evaluable = false;

    //是否公开，默认私密
    private Boolean overt = false;

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
        return overt;
    }

    public void setState(Boolean state) {
        this.overt = state;
    }

    @Override
    public String toString() {
        return "BlogVo{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", evaluable=" + evaluable +
                ", state=" + overt +
                '}';
    }
}
