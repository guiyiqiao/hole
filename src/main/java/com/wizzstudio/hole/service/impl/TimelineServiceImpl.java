package com.wizzstudio.hole.service.impl;

import com.wizzstudio.hole.mapper.BlogMapper;
import com.wizzstudio.hole.mapper.EchoMapper;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.model.Echo;
import com.wizzstudio.hole.model.vo.TimeLineVo;
import com.wizzstudio.hole.service.TimelineService;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        List<TimeLineVo> collect1 = blogList.stream().map(x -> {
            TimeLineVo timeLineVo = new TimeLineVo();
            BeanUtils.copyProperties(x, timeLineVo);
            return timeLineVo;
        }).collect(Collectors.toList());

        //我发出的回声与我收到的回声
        List<Echo> echoList = echoMapper.timeline(userId);
        List<TimeLineVo> collect2 = echoList.stream().map(x -> {
            TimeLineVo timeLineVo = new TimeLineVo();
            BeanUtils.copyProperties(x, timeLineVo);
            return timeLineVo;
        }).collect(Collectors.toList());

        List<TimeLineVo> timeLineVos = new ArrayList<>();
        timeLineVos.addAll(collect1);
        timeLineVos.addAll(collect2);
        Collections.sort(timeLineVos,(o1, o2) -> {
            long ret = o2.getPublishTime().getTime() - o1.getPublishTime().getTime();
            return   (int) ret;
        });
        return HoleResult.success(timeLineVos);
    }
}
