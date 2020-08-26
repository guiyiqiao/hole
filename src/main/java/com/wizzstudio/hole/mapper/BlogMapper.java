package com.wizzstudio.hole.mapper;


import com.wizzstudio.hole.model.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {

    int insertBlog(Blog blog);

    List<Blog> listBlogByUserId(Integer userId);

    List<Blog> listBlogByComment(Integer userId);
}
