package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.service.BlogService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.TokenUtil;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 18:53
 * @Version 1.0
 */
@RestController
@RequestMapping("hole/blog")
public class BlogController {

    @Autowired
    private TokenUtil tokenUtil;

    @Resource
    private BlogService blogService;
    /**
     * 需求2.添加心事接口
     * @param blog
     * @return
     */
    @UserLogin
    @PostMapping
    public HoleResult addBlog(Blog blog, HttpServletRequest request){

        blog.setUserId(getUserId(request));

        int ret = blogService.insertBlog(blog);
        if(ret > 0)
            return HoleResult.success();
        else
            return HoleResult.failure();
    }

    /**
     * 需求4.查询我倾诉的心事
     * @return
     */
    @GetMapping
    @UserLogin
    public HoleResult getBlog(HttpServletRequest request){

        List<Blog> blogList = blogService.listBlogByUserId(getUserId(request));
        return HoleResult.success(blogList);
    }

    /**查询我回应的心事
     * 需求5、
     * @param request
     * @return
     */
    @GetMapping("user")
    @UserLogin
    public HoleResult getBlogByUser(HttpServletRequest request){

        return HoleResult.success(blogService.getBlogByUserComment(getUserId(request)));
    }

    private Integer getUserId(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Integer userId = tokenUtil.getUserId(token);
        return userId;
    }
}
