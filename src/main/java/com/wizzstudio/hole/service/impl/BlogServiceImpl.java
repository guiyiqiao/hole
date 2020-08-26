package com.wizzstudio.hole.service.impl;

import com.wizzstudio.hole.mapper.BlogMapper;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.model.constant.BlogConstant;
import com.wizzstudio.hole.service.BlogService;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 需求二、添加心事matter
     * @param blog
     * @return
     */
    @Override
    public HoleResult addMatter(Blog blog) {
        int ret = blogMapper.insertSelective(blog);
        if(ret > 0)
            return HoleResult.success();
        else
            return HoleResult.failure("添加失败，请稍后重试！");
    }

    /**
     * 需求二、对心事点击hug，使用缓存防止用户重复点击
     * 待完成
     * @param blogId
     * @return
     */
    @Override
    public HoleResult addHug(Integer blogId,Integer userId) {
        Object o = redisTemplate.boundHashOps(BlogConstant.getBlogHugUserKey(blogId))
                .get(userId);
        if(o !=null){
            return HoleResult.failure("不可重复拥抱");
        }
        //添加hug数量，并将userId存入最近hug用户
        redisTemplate.boundValueOps(BlogConstant.getBlogHugKey(blogId))
                .increment();
        redisTemplate.boundHashOps(BlogConstant.getBlogHugUserKey(blogId))
                .putIfAbsent(userId,1);
        return null;
    }

    /**
     * 需求二、举报心事
     * 待完成
     * @return
     */
    @Override
    public HoleResult report(){
        return null;
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
