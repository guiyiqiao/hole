package com.wizzstudio.hole.service;

import com.github.pagehelper.PageInfo;
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

    Blog getBlogById(Integer blogId);

    HoleResult addMatter(Blog blog);

    HoleResult addHug(Integer blogId);

    int getHug(Integer blogId);

    HoleResult listMyBlog(Integer userId,int pageNum,int pageSize);

    HoleResult deleteBlog(Integer blogId,Integer userId);

    HoleResult listOvertBlog(int pageNum,int pageSize);

    HoleResult listReplyBlog(Integer userId, int pageNum, int pageSize);
}
