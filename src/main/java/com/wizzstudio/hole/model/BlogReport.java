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
}
