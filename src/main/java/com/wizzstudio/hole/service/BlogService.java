package com.wizzstudio.hole.service;

import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.model.BlogReport;
import com.wizzstudio.hole.util.HoleResult;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @Author 桂乙侨
 * @Date 2020/8/24 19:37
 * @Version 1.0
 */
public interface BlogService {

    HoleResult addMatter(Blog blog);

    HoleResult addHug(Integer blogId,Integer userId);

    HoleResult report(BlogReport report);

    List<Blog> listBlogByUserId(Integer userId);

    List<Blog> getBlogByUserComment(Integer userId);
}
