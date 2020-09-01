package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.model.vo.BlogVo;
import com.wizzstudio.hole.service.BlogService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.HoleUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

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



    //@GetMapping
    public HoleResult getBlogById(@RequestParam("blogId") Integer blogId){
        return blogService.getBlogById(blogId);
    }



    /**
     * 功能一.添加心事接口
     * @param blogVo
     * @return
     */
    @UserLogin
    @PostMapping
    public HoleResult addBlog(@Valid BlogVo blogVo, BindingResult bindingResult,HttpServletRequest request){
        HoleResult valid = HoleUtils.valid(bindingResult);
        if(valid != null)
            return valid;
        Blog blog = Blog.BlogBuilder.aBlog()
                .withUserId(HoleUtils.getUserId(request))
                .withHug(0)
                .withValid(true)
                .withPublishTime(new Date())
                .build();
        BeanUtils.copyProperties(blogVo,blog);
        return blogService.addMatter(blog);
    }

    /**
     * 需求2.对心事添加hug
     * 允许重复拥抱
     * @param blogId
     * @return
     */

    //@PostMapping("hug/{blogId}")
    public HoleResult addHug(@PathVariable("blogId") Integer blogId){
        return blogService.addHug(blogId);
    }



    /**
     * 需求4.查询我倾诉的心事
     * @return
     */
    //@GetMapping("mine")
    @UserLogin
    public HoleResult getBlog(HttpServletRequest request, int pageNum,int pageSize){

        return blogService.listMyBlog(HoleUtils.getUserId(request),pageNum,pageSize);
    }

    /**查询我回应的心事
     * 需求四
     * @param request
     * @return
     */
    //@GetMapping("other")
    @UserLogin
    public HoleResult getBlogByUser(HttpServletRequest request){

        return HoleResult.success(blogService.getBlogByUserComment(HoleUtils.getUserId(request)));
    }

    @UserLogin
    //@DeleteMapping
    public HoleResult deleteBlog(@RequestParam("blogId") Integer blogId,
                                 HttpServletRequest request){
        //待完成
        return null;
    }
}
