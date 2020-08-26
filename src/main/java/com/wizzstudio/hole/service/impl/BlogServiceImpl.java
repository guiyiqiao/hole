package com.wizzstudio.hole.service.impl;

import com.wizzstudio.hole.mapper.BlogMapper;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 桂乙侨
 * @Date 2020/8/24 19:38
 * @Version 1.0
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;


    @Override
    public int insertBlog(Blog blog) {
        return blogMapper.insertBlog(blog);
    }

    @Override
    public List<Blog> listBlogByUserId(Integer userId) {
        return blogMapper.listBlogByUserId(userId);
    }

    @Override
    public List<Blog> getBlogByUserComment(Integer userId) {
        return blogMapper.listBlogByComment(userId);
    }
}
