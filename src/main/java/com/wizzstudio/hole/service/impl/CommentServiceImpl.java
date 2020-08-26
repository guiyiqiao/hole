package com.wizzstudio.hole.service.impl;

import com.wizzstudio.hole.model.Comment;
import com.wizzstudio.hole.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author 桂乙侨
 * @Date 2020/8/24 20:43
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentService commentService;

    @Override
    public int insertComment(Comment comment) {
        return commentService.insertComment(comment);
    }
}
