package com.wizzstudio.hole.mapper;


import com.wizzstudio.hole.model.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    int insertComment(Comment comment);
}
