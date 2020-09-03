package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.PassToken;
import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.model.vo.BlogVo;
import com.wizzstudio.hole.service.BlogService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.HoleUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "树洞有声 心事相关接口")
@RestController
@RequestMapping("hole/blog")
public class BlogController {
    @Resource
    private BlogService blogService;



    @GetMapping
    @PassToken
    @ApiOperation(value = "无需登陆；通过心事id查询心事详情信息")
    public HoleResult getBlogById(@RequestParam("blogId") Integer blogId){
        return blogService.getBlogById(blogId);
    }



    /**
     * 功能一.添加心事接口
     * @param blogVo
     * @return
     */
    @ApiOperation(value = "需要登陆；发布心事，通过上传的参数决定是否公开是否可评论")
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

    @ApiOperation(value = "无需登陆；心事拥抱接口")
    @PassToken
    @PostMapping("hug/{blogId}")
    public HoleResult addHug(@PathVariable("blogId") Integer blogId){
        return blogService.addHug(blogId);
    }



    /**
     * 需求4.查询我倾诉的心事
     * @return
     */
    @ApiOperation(value = "需要登陆；查询我发布的心事")
    @GetMapping("mine")
    @UserLogin
    public HoleResult listMyBlog(HttpServletRequest request,
                                 @RequestParam("pageNum") int pageNum,
                                 @RequestParam("ageSize") int pageSize){

        return blogService.listMyBlog(HoleUtils.getUserId(request),pageNum,pageSize);
    }

    /**查询我回应的心事
     * 需求四
     * @param request
     * @return
     */
    @ApiOperation(value = "需要登陆；查询我回应的心事")
    @GetMapping("other")
    @UserLogin
    public HoleResult getBlogByUser(HttpServletRequest request,
                                    @RequestParam("pageNum") int pageNum,
                                    @RequestParam("ageSize") int pageSize){

        return blogService.listReplyBlog(HoleUtils.getUserId(request),pageNum,pageSize);
    }

    @UserLogin
    @DeleteMapping
    @ApiOperation(value = "需要登陆；删除我的某一心事")
    public HoleResult deleteBlog(@RequestParam("blogId") Integer blogId,
                                 HttpServletRequest request){

        return blogService.deleteBlog(blogId,HoleUtils.getUserId(request));
    }

    /**
     * 查询心事大厅
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "不需要登陆；心事大厅")
    @GetMapping("hall")
    @PassToken
    public HoleResult listOvertBlog( @RequestParam("pageNum") int pageNum,
                                     @RequestParam("pageSize") int pageSize){
        return blogService.listOvertBlog(pageNum,pageSize);
    }
}
