package com.wizzstudio.hole.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wizzstudio.hole.mapper.BlogMapper;
import com.wizzstudio.hole.mapper.BlogReportMapper;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.model.BlogReport;
import com.wizzstudio.hole.model.constant.CacheKey;
import com.wizzstudio.hole.service.BlogService;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.data.redis.core.BoundValueOperations;
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


    /**
     * 需求2 查询心事详情
     * @param blogId
     * @return
     */
    @Override
    public HoleResult getBlogById(Integer blogId) {

        return HoleResult.success(blogMapper.selectByPrimaryKey(blogId));
    }




    /**
     * 需求二、查询心事列表
     * @return
     */
    @Override
    public HoleResult listBlog(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        //设置分页数据，该方法之后的第一个select方法会进行分页
        List<Blog> listBlog = blogMapper.selectAll();
        PageInfo<Blog> pageInfo = new PageInfo<>(listBlog);
        return HoleResult.success(pageInfo);
    }

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
     * 需求二、对心事点击hug，使用缓存防止短时间数据库不断写入
     * 还未实现将hug数写入数据库(hug比较频繁,修改量小,且数据不需要极高的可靠性，暂定为定期写入缓存中的数据
     * @param blogId
     * @return
     */
    @Override
    public HoleResult addHug(Integer blogId) {
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        if(blog == null)
            return HoleResult.failure("心事不存在！");
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(CacheKey.getBlogHugKey(blogId));
        boundValueOperations
                .setIfAbsent(0,2,TimeUnit.DAYS);
        boundValueOperations.increment();
        return HoleResult.success();
    }


    /**
     * 需求四 查询我的心事
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public HoleResult listMyBlog(Integer userId, int pageNum, int pageSize) {
        Blog blog = new Blog();
        blog.setUserId(userId);
        blog.setValid(true);

        PageHelper.startPage(pageNum,pageSize);
        List<Blog> select = blogMapper.select(blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(select);
        return HoleResult.success(pageInfo);
    }

    /**
     * 删除心事 以及 回声，待完成
     * @param blog
     * @return
     */
    @Override
    public HoleResult deleteBlog(Blog blog) {
        blog.setValid(false);
        int ret = blogMapper.updateByPrimaryKeySelective(blog);
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
