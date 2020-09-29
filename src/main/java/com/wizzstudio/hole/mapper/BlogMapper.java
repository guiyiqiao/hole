package com.wizzstudio.hole.mapper;


import com.wizzstudio.hole.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogMapper extends tk.mybatis.mapper.common.Mapper<Blog> {

    int deleteBlog(Integer blogId,Integer userId);

    List<Blog> listReplyBlog(Integer userId);

    int updateBatch(@Param("map") Map<Integer,Integer> map);
}
