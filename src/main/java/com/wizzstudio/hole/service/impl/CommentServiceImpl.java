package com.wizzstudio.hole.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wizzstudio.hole.mapper.CommentMapper;
import com.wizzstudio.hole.mapper.CommentReportMapper;
import com.wizzstudio.hole.model.Comment;
import com.wizzstudio.hole.model.CommentReport;
import com.wizzstudio.hole.model.constant.CacheKey;
import com.wizzstudio.hole.service.CommentService;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author 桂乙侨
 * @Date 2020/8/24 20:43
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 需求三、添加回声
     * @param comment
     * @return
     */
    @Override
    public HoleResult addComment(Comment comment) {
        int ret = commentMapper.insertSelective(comment);
        return ret > 0 ? HoleResult.success():HoleResult.failure();
    }

    /**
     * 需求三、列举某一心事的公开回声
     * @param blogId
     * @return
     */
    @Override
    public HoleResult listByBlogId(Integer blogId,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Comment comment = Comment.CommentBuilder.aComment()
                .withBlogId(blogId)
                .withValid(true)
                .withOvert(true)
                .build();
        List<Comment> comments = commentMapper.select(comment);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return HoleResult.success(pageInfo);
    }



    /**
     * 需求三、对回声点击喜欢，使用缓存防止用户重复点击
     * 还未实现将thank数写入数据库(thank比较频繁,修改量小,且数据不需要极高的可靠性，暂定为定期写入缓存中的数据）
     * @param commentId
     * @return
     */
    @Override
    public HoleResult thank(Integer commentId,Integer userId){
        Comment comment = commentMapper.selectByPrimaryKey(commentId);

        if(comment == null)
            return HoleResult.failure("回声不存在！");
        Boolean hasKey = redisTemplate.hasKey(CacheKey.getCommentThankUserKey(commentId));
        if(!hasKey){
            //如果没有用户列表hash键，则新建一个,过期时间默认为30天
            redisTemplate.boundHashOps(CacheKey.getCommentThankUserKey(commentId))
                    .expire(30, TimeUnit.DAYS);
        }else{
            Object o = redisTemplate.boundHashOps(CacheKey.getBlogHugUserKey(commentId))
                    .get(userId);
            if(o !=null){
                return HoleResult.failure("不可重复拥抱");
            }
        }

        //添加hug数量，并将userId存入最近hug用户
        redisTemplate.boundValueOps(CacheKey.getCommentThankKey(commentId))
                .increment();
        redisTemplate.boundHashOps(CacheKey.getCommentThankKey(commentId))
                .putIfAbsent(userId,1);
        return HoleResult.success();
    }

}
