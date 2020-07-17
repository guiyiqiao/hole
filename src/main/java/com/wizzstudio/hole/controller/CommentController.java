package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.model.Comment;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 18:53
 * @Version 1.0
 */

@RestController
@RequestMapping("hole/comment")
public class CommentController {

    /**
     * 添加评论/私信接口
     * @param comment
     * @return
     */
    public HoleResult addBlog(Comment comment){
        return null;
    }
}
