package com.wizzstudio.hole.service;

import com.wizzstudio.hole.model.Comment;
import com.wizzstudio.hole.model.CommentReport;
import com.wizzstudio.hole.util.HoleResult;
import io.swagger.models.auth.In;

/**
 * @Author 桂乙侨
 * @Date 2020/8/24 20:42
 * @Version 1.0
 */
public interface CommentService {
    HoleResult addComment(Comment comment);

    HoleResult listByBlogId(Integer blogId,int pageNum,int pageSize);

    HoleResult thank(Integer commentId,Integer userId);

}
