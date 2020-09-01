package com.wizzstudio.hole.schedule;

import com.wizzstudio.hole.model.constant.CacheKey;
import com.wizzstudio.hole.service.BlogService;
import com.wizzstudio.hole.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.misc.Cache;

import java.util.HashSet;

/**
 * @Author 桂乙侨
 * @Date 2020/8/31 8:46
 * @Version 1.0
 */
@Component
public class HugThankSchedule {

    @Autowired
    private BlogService blogService;

    @Autowired
    private EchoService echoService;

    @Autowired
    private RedisTemplate redisTemplate;

    //每天上午1点30触发
    //@Scheduled(cron = "0 30 1 ? * *")
    public void updateHug(){
        HashSet<Integer> set = (HashSet<Integer>) redisTemplate.boundSetOps(CacheKey.getEchoThankSetKey()).members();

    }

    //每天上午1点30触发
    //@Scheduled(cron = "0 30 1 ? * *")
    public void updateThank(){
        redisTemplate.boundHashOps("sda");
    }
}
