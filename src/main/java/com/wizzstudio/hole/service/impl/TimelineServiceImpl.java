package com.wizzstudio.hole.service.impl;

import com.wizzstudio.hole.mapper.BlogMapper;
import com.wizzstudio.hole.mapper.CommentMapper;
import com.wizzstudio.hole.mapper.EchoMapper;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.model.Comment;
import com.wizzstudio.hole.model.Echo;
import com.wizzstudio.hole.model.TimelineComparable;
import com.wizzstudio.hole.service.TimelineService;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

/**
 * @Author 桂乙侨
 * @Date 2020/8/28 10:20
 * @Version 1.0
 */
@Service
public class TimelineServiceImpl implements TimelineService {

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private EchoMapper echoMapper;

    /**
     * 时光轴 将我发布的心事，我收到的回声，我发出的回声进行排序
     * @param userId
     * @return
     */
    @Override
    public HoleResult listTimeline(Integer userId) {
        //我发布的心事
        Blog blog = new Blog();
        blog.setValid(true);
        blog.setUserId(userId);
        List<Blog> blogList = blogMapper.select(blog);
        //我发出的回声与我收到的回声
        List<Echo> echoList = echoMapper.timeline(userId);
        List<TimelineComparable> list = new ArrayList<>();
        list.addAll(blogList);
        list.addAll(echoList);

        Collections.sort(list,(o1, o2) -> {
            long ret = o1.getDate().getTime() -o2.getDate().getTime();
          return   (int) ret;
        });
        return HoleResult.success(list);
    }
}
