package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.model.BlogReport;
import com.wizzstudio.hole.model.vo.BlogVo;
import com.wizzstudio.hole.service.BlogService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.TokenUtil;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
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
     * @param blogVo
     * @return
     */
    @UserLogin
    @PostMapping
    public HoleResult addBlog(@Valid BlogVo blogVo, HttpServletRequest request){
        Blog blog = new Blog();
        BeanUtils.copyProperties(blog,blogVo);
        blog.setUserId(getUserId(request));
        blog.setReleaseTime(new Date());

        return blogService.addMatter(blog);
    }

    /**
     * 需求2.对心事添加hug
     * 将userId存储于缓存中，以防止短期重复hug
     * @param blogId
     * @return
     */

    @PostMapping("hug/{blogId}")
    @UserLogin
    public HoleResult addHug(@PathVariable("blogId") Integer blogId,HttpServletRequest request){
        return blogService.addHug(blogId,getUserId(request));
    }

    /**
     * 需求2.对心事的举报操作
     * report 记为举报
     * @param blogId  被举报的心事id
     * @param content 举报类别/内容
     * @return
     */
    @PostMapping("report/{blogId}")
    @UserLogin
    public HoleResult report(@PathVariable("blogId") Integer blogId,
                             @RequestParam("content") String content,
                             HttpServletRequest request){
        BlogReport blogReport = BlogReport.BlogReportBuilder.aBlogReport()
                .withUserId(getUserId(request))
                .withBlogId(blogId)
                .withContent(content)
                .build();
        return  blogService.report(blogReport);
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
