package com.wizzstudio.hole.service.impl;

import com.wizzstudio.hole.mapper.BlogMapper;
import com.wizzstudio.hole.mapper.BlogReportMapper;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.model.BlogReport;
import com.wizzstudio.hole.model.constant.BlogConstant;
import com.wizzstudio.hole.service.BlogService;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private BlogReportMapper blogReportMapper;
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
     * 还未实现将hug数写入数据库(hug比较频繁,修改量小,且数据不需要极高的可靠性，暂定为定期写入缓存中的数据
     * @param blogId
     * @return
     */
    @Override
    public HoleResult addHug(Integer blogId,Integer userId) {
        Boolean hasKey = redisTemplate.hasKey(BlogConstant.getBlogHugUserKey(blogId));
        if(!hasKey){
            //如果没有用户列表hash键，则新建一个,过期时间默认为30天
            redisTemplate.boundHashOps(BlogConstant.getBlogHugUserKey(blogId))
                    .expire(30, TimeUnit.DAYS);
        }else{
            Object o = redisTemplate.boundHashOps(BlogConstant.getBlogHugUserKey(blogId))
                    .get(userId);
            if(o !=null){
                return HoleResult.failure("不可重复拥抱");
            }
        }

        //添加hug数量，并将userId存入最近hug用户
        redisTemplate.boundValueOps(BlogConstant.getBlogHugKey(blogId))
                .increment();
        redisTemplate.boundHashOps(BlogConstant.getBlogHugUserKey(blogId))
                .putIfAbsent(userId,1);
        return HoleResult.success();
    }

    /**
     * 需求二、举报心事
     * 待完成
     * @return
     */
    @Override
    public HoleResult report(BlogReport report){
        int ret = blogReportMapper.insertSelective(report);
        return ret > 0 ? HoleResult.success():HoleResult.failure("拥抱失败请重试！");
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
