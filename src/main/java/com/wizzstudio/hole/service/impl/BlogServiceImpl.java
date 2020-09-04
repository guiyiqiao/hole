package com.wizzstudio.hole.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wizzstudio.hole.annotation.RedisCache;
import com.wizzstudio.hole.mapper.BlogMapper;
import com.wizzstudio.hole.mapper.BlogReportMapper;
import com.wizzstudio.hole.mapper.EchoMapper;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.model.BlogReport;
import com.wizzstudio.hole.model.Echo;
import com.wizzstudio.hole.model.constant.CacheKey;
import com.wizzstudio.hole.service.BlogService;
import com.wizzstudio.hole.service.EchoService;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Author 桂乙侨
 * @Date 2020/8/24 19:38
 * @Version 1.0
 */
@Service
public class BlogServiceImpl implements BlogService {

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private EchoMapper echoMapper;
    /**
     * 需求2 查询心事详情
     * @param blogId
     * @return
     */
    @Override
    public Blog getBlogById(Integer blogId) {
        Object o = redisTemplate.boundValueOps(CacheKey.getBlogKey(blogId)).get();
        if(o == null){
            Blog blog = blogMapper.selectByPrimaryKey(blogId);
            if(blog != null)
                redisTemplate.boundValueOps(CacheKey.getBlogKey(blogId)).set(blog,7200+(random.nextInt()%60),TimeUnit.SECONDS);
            return null;
        }
        return (Blog) o;
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
        redisTemplate.boundHashOps(CacheKey.BLOG_HUG_PREFIX).increment(blogId,1);
        return HoleResult.success();
    }

    @Override
    public int getHug(Integer blogId) {
        final Object o = redisTemplate.boundHashOps(CacheKey.BLOG_HUG_PREFIX).get(blogId);

        return o == null?0:(Integer) o;
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
     * 需求四 查询我回应的心事
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public HoleResult listReplyBlog(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<Blog> select = blogMapper.listReplyBlog(userId);
        PageInfo<Blog> pageInfo = new PageInfo<>(select);
        return HoleResult.success(pageInfo);
    }

    /**
     * 删除心事 和对应的回声
     * @param blogId
     * @return
     */
    @Override
    @Transactional
    public HoleResult deleteBlog(Integer blogId,Integer userId) {
        echoMapper.deleteEcho(blogId,userId);
        Blog blog = new Blog();
        blog.setValid(false);
        blog.setId(blogId);
        int ret = blogMapper.deleteBlog(blogId,userId);

        return ret > 0? HoleResult.success():HoleResult.failure();
    }

    @Override
    public HoleResult listOvertBlog(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Condition condition = new Condition(Blog.class);
        condition.orderBy("hug").desc().orderBy("publishTime").desc();
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("overt",true);
        criteria.andEqualTo("valid",true);
        List<Blog> list = blogMapper.selectByExample(condition);
        return HoleResult.success(list);
    }
}
