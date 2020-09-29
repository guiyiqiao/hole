package com.wizzstudio.hole.schedule;

import com.wizzstudio.hole.mapper.BlogMapper;
import com.wizzstudio.hole.mapper.EchoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 桂乙侨
 * @Date 2020/8/31 8:46
 * @Version 1.0
 */
@Component
public class HugThankSchedule {

    private static Logger log = LoggerFactory.getLogger(HugThankSchedule.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private EchoMapper echoMapper;


    @Resource(name = "hugMap")
    private ConcurrentHashMap<Integer,Integer> hugMap ;
    @Resource(name = "thankMap")
    private ConcurrentHashMap<Integer,Integer> thankMap ;
    //每天上午1点30触发
    @Scheduled(cron = "0 30 1 ? * *")
    @Transactional
    public void updateHug(){
        log.info(new Date()+"执行拥抱数写回数据库");
        //Map<Integer,Integer> map = redisTemplate.boundHashOps(CacheKey.BLOG_HUG_PREFIX).entries();
        //redisTemplate.delete(CacheKey.BLOG_HUG_PREFIX);
        blogMapper.updateBatch(hugMap);

    }

    //每天上午1点30触发
    @Scheduled(cron = "0 30 1 ? * *")
    public void updateThank(){
        log.info(new Date()+"执行感谢数写回数据库");
        //Map<Integer,Integer> map = redisTemplate.boundHashOps(CacheKey.ECHO_THANK_PREFIX).entries();
        //redisTemplate.delete(CacheKey.ECHO_THANK_PREFIX);
        echoMapper.updateBatch(thankMap);
    }
}
