package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.model.Comment;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.TokenUtil;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 18:53
 * @Version 1.0
 */

@RestController
@RequestMapping("hole/comment")
public class CommentController {
    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 需求3.添加评论/私密评论接口
     * @param comment  用户id，blog的id，内容
     * @return
     */
    @UserLogin
    public HoleResult addComment(Comment comment, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Integer userId = tokenUtil.getUserId(token);

        return null;
    }
}
