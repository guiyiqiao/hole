package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.model.BlogReport;
import com.wizzstudio.hole.model.vo.BlogVo;
import com.wizzstudio.hole.service.BlogService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.TokenUtil;
import com.wizzstudio.hole.util.UserIdUtil;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    @Resource
    private BlogService blogService;

   /* *//**
     * 需求二、心事大厅查询心事列表
     * @return
     *//*
    @GetMapping
    public HoleResult listBlog(@RequestParam("pageSize") int pageSize,
                               @RequestParam("pageNum") int pageNum){
        return blogService.listBlog(pageNum, pageSize);
    }
*/

    @GetMapping
    public HoleResult getBlogById(@RequestParam("blogId") Integer blogId){
        return blogService.getBlogById(blogId);
    }



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
        blog.setUserId(UserIdUtil.getUserId(request));
        blog.setPublishTime(new Date());

        return blogService.addMatter(blog);
    }

    /**
     * 需求2.对心事添加hug
     * 允许重复拥抱
     * @param blogId
     * @return
     */

    @PostMapping("hug/{blogId}")
    public HoleResult addHug(@PathVariable("blogId") Integer blogId){
        return blogService.addHug(blogId);
    }



    /**
     * 需求4.查询我倾诉的心事
     * @return
     */
    @GetMapping("mine")
    @UserLogin
    public HoleResult getBlog(HttpServletRequest request, int pageNum,int pageSize){

        return blogService.listMyBlog(UserIdUtil.getUserId(request),pageNum,pageSize);
    }

    /**查询我回应的心事
     * 需求四
     * @param request
     * @return
     */
    @GetMapping("other")
    @UserLogin
    public HoleResult getBlogByUser(HttpServletRequest request){

        return HoleResult.success(blogService.getBlogByUserComment(UserIdUtil.getUserId(request)));
    }

    @UserLogin
    @DeleteMapping
    public HoleResult deleteBlog(@RequestParam("blogId") Integer blogId,
                                 HttpServletRequest request){
        //待完成
        return null;
    }
}
