package com.wizzstudio.hole.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wizzstudio.hole.mapper.EchoMapper;
import com.wizzstudio.hole.model.Blog;
import com.wizzstudio.hole.model.Comment;
import com.wizzstudio.hole.model.Echo;
import com.wizzstudio.hole.model.constant.CacheKey;
import com.wizzstudio.hole.service.EchoService;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author 桂乙侨
 * @Date 2020/8/29 15:36
 * @Version 1.0
 */
@Service
public class EchoServiceImpl implements EchoService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private EchoMapper echoMapper;
    @Override
    public HoleResult addEcho(Echo echo) {
        int ret = echoMapper.insertSelective(echo);
        return ret > 0 ? HoleResult.success():HoleResult.failure();
    }

    /**
     * 需求三、对回声点击喜欢，使用缓存防止用户重复点击
     * 还未实现将thank数写入数据库(thank比较频繁,修改量小,且数据不需要极高的可靠性，暂定为定期写入缓存中的数据）
     * @param echoId
     * @return
     */
    @Override
    public HoleResult thank(Integer echoId){
        redisTemplate.boundHashOps(CacheKey.ECHO_THANK_PREFIX).increment(echoId,1);
        return HoleResult.success();
    }

    @Override
    public int getThank(Integer echoId) {
        final Object o = redisTemplate.boundHashOps(CacheKey.ECHO_THANK_PREFIX).get(echoId);

        return o == null ? 0:(Integer) o;
    }

    /**
     * 需求三、列举某一心事的公开回声
     * @param blogId
     * @return
     */
/*    @Override
    public HoleResult listByBlogId(Integer blogId,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Echo echo = Echo.EchoBuilder.anEcho()
                .withBlogId(blogId)
                .withValid(true)
                .withOvert(true)
                .build();
        List<Echo> echoes = echoMapper.select(echo);
        PageInfo<Echo> pageInfo = new PageInfo<>(echoes);
        return HoleResult.success(pageInfo);
    }*/

    /**
     * 心事拥有者可选泽公开回声
     * @param echoId
     * @param userId
     * @return
     */
    @Override
    public HoleResult openEcho(Integer echoId, Integer userId) {
        int ret = echoMapper.openEcho(echoId,userId);
        return ret > 0 ? HoleResult.success():HoleResult.failure("请稍后重试");
    }

    /**
     * 删除单条回声
     * @param echoId
     * @param userId
     * @return
     */
    @Override
    public HoleResult deleteEcho(Integer echoId, Integer userId) {
        Example example = new Example(Echo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",echoId);
        criteria.andEqualTo("user_id",userId);
        Echo echo = new Echo();
        echo.setValid(false);
        int ret = echoMapper.updateByExampleSelective(echo,example);
        return ret > 0 ? HoleResult.success():HoleResult.failure();
    }

    @Override
    public HoleResult listOvertEcho(int pageNum, int pageSize) {
            PageHelper.startPage(pageNum,pageSize);

            Condition condition = new Condition(Echo.class);
            //待处理
            condition.orderBy("thank").desc().orderBy("publishTime").desc();
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("overt",true);
            criteria.andEqualTo("valid",true);
            List<Echo> list = echoMapper.selectByExample(condition);
            return HoleResult.success(list);

    }
}
