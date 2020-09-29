package com.wizzstudio.hole.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author 桂乙侨
 * @Date 2020/9/3 20:14
 * @Version 1.0
 */
@ApiModel(value = "时光轴返回对象")
public class TimeLineVo {
    @ApiModelProperty(value = "心事或回声id号")
    private Integer id;

    @ApiModelProperty(value = "该心事或回声所属用户id（若id不等于当前id则为’我‘收到的回声")
    private Integer userId;

    @ApiModelProperty(value = "若此对象为回声，则此字段代表心事id；否则为空代表心事，不存在此字段;")
    private Integer blogId;

    @ApiModelProperty(value = "若此对象为心事，则此字段代表心事的标题；否则为空代表回声;")
    private String title;

    @ApiModelProperty(value = "心事或回声的内容")
    private String content;

    //发布时间
    @ApiModelProperty(value = "心事或回声的发布时间 （date类型）")
    private Date publishTime;


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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "TimeLineVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", blogId=" + blogId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishTime=" + publishTime +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
