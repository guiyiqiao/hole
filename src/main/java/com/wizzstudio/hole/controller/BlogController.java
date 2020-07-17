package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 18:53
 * @Version 1.0
 */
@RestController
@RequestMapping("hole/blog")
public class BlogController {

    /**
     * 添加心事接口
     * @param blog
     * @return
     */
    public HoleResult addBlog(Blog blog){
        return null;
    }

    /**
     * 我倾诉的心事
     * @return
     */
    public HoleResult getBlog(){
        return null;
    }
}
