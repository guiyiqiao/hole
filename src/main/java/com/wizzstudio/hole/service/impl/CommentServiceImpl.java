package com.wizzstudio.hole.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wizzstudio.hole.mapper.CommentMapper;
import com.wizzstudio.hole.model.Comment;
import com.wizzstudio.hole.service.CommentService;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * @Author 桂乙侨
 * @Date 2020/8/24 20:43
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;


    /**
     * 需求三、添加评论
     * @param comment
     * @return
     */
    @Override
    public HoleResult addComment(Comment comment) {
        int ret = commentMapper.insertSelective(comment);
        return ret > 0 ? HoleResult.success():HoleResult.failure();
    }







}
