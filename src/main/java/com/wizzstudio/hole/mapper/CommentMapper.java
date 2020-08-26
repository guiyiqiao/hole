package com.wizzstudio.hole.mapper;


import com.wizzstudio.hole.model.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends tk.mybatis.mapper.common.Mapper<Comment> {

    int insertComment(Comment comment);
}
