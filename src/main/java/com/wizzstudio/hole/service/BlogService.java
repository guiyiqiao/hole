package com.wizzstudio.hole.service;

import com.wizzstudio.hole.model.Blog;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @Author 桂乙侨
 * @Date 2020/8/24 19:37
 * @Version 1.0
 */
public interface BlogService {

    int insertBlog(Blog blog);

    List<Blog> listBlogByUserId(Integer userId);

    List<Blog> getBlogByUserComment(Integer userId);
}
