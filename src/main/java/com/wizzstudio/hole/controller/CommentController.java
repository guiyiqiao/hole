package com.wizzstudio.hole.controller;


import com.wizzstudio.hole.model.Comment;
import com.wizzstudio.hole.service.CommentService;
import com.wizzstudio.hole.util.HoleResult;

import com.wizzstudio.hole.util.UserIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 18:53
 * @Version 1.0
 */

@RestController
@RequestMapping("hole/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     * 需求三 心事 发布评论
     * @param blogId
     * @param content 评论内容
     * @return
     */
    @PostMapping
    public HoleResult addComment(@RequestParam("blogId") Integer blogId,
                                 @RequestParam("content") String content,
                                 HttpServletRequest request){
        Comment comment = Comment.CommentBuilder.aComment()
                .withBlogId(blogId)
                .withContent(content)
                .withPublishTime(new Date())
                .withReport(false)
                .withUserId(UserIdUtil.getUserId(request))
                .withValid(true)
                .build();
        return commentService.addComment(comment);

    }

}
