package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.model.Comment;
import com.wizzstudio.hole.model.CommentReport;
import com.wizzstudio.hole.model.vo.CommentVo;
import com.wizzstudio.hole.service.CommentService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.UserIdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
     * 需求3.发布回声
     * @param commentVo  blog的id，内容
     * @return
     */
    @UserLogin
    @PostMapping
    public HoleResult addComment(@Valid CommentVo commentVo, HttpServletRequest request){
        Comment comment  = new Comment();
        BeanUtils.copyProperties(comment,commentVo);
        comment.setThank(0);
        comment.setReport(false);
        comment.setPublishTime(new Date());
        comment.setUserId(UserIdUtil.getUserId(request));
        return commentService.addComment(comment);
    }

    /**
     * 需求三 查询公开回声
     * @param blogId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping
    public HoleResult listCommentByBlogId(@RequestParam("blogId") Integer blogId,
                                          @RequestParam("pageSize") int pageSize,
                                          @RequestParam("pageNum") int pageNum){
        return commentService.listByBlogId(blogId,pageNum,pageSize);
    }



}
