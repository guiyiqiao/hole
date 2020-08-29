package com.wizzstudio.hole.service;

import com.wizzstudio.hole.model.Echo;
import com.wizzstudio.hole.util.HoleResult;

/**
 * @Author 桂乙侨
 * @Date 2020/8/29 15:36
 * @Version 1.0
 */
public interface EchoService {

    HoleResult addEcho(Echo echo);

    HoleResult thank(Integer commentId);

    HoleResult listByBlogId(Integer blogId,int pageNum,int pageSize);
}
