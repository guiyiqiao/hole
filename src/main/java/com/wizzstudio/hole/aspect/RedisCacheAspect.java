package com.wizzstudio.hole.aspect;

import com.wizzstudio.hole.annotation.RedisCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.ExpressionParser;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author 桂乙侨
 * @Date 2020/9/3 21:51
 * @Version 1.0
 */
//@Component
//@Aspect
@Deprecated
public class RedisCacheAspect {

    private static Logger log  = LoggerFactory.getLogger(RedisCacheAspect.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(com.wizzstudio.hole.annotation.RedisCache)")
    public Object queryCache(ProceedingJoinPoint pjp) throws Throwable {
        log.info("**********方法执行前增强***************");
        long startTime = System.currentTimeMillis();
        // 获取方法上的注解key的值
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RedisCache redisCache = method.getAnnotation(RedisCache.class);

        String prefix = redisCache.prefix();
        int expire = redisCache.expire();

        Object[] args = pjp.getArgs(); // 参数值
        //args.getClass().get
        String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method); // 参数名

       /* if (null != object) {
            log.info("**********从Redis中查到了数据**********");
            log.info("Redis的KEY值:" + key);
            log.info("Redis的VALUE值:" + object.toString());
            return object;
        }
        long endTime = System.currentTimeMillis();
        log.info("Redis缓存AOP处理所用时间:" + (endTime - startTime));
        log.info("**********没有从Redis查到数据**********");
        try {
            object = pjp.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("**********方法执行后增强***************");
        log.info("**********开始从MySQL查询数据**********");
        *//*
         * 后置：将数据库查到的数据保存到Redis
         *//*
        boolean isSave = RedisUtils.set(key, object);
        if (isSave) {
            log.info("**********数据成功保存到Redis缓存!!!**********");
            log.info("Redis的KEY值:" + key);
            log.info("REDIS的VALUE值:" + object.toString());
        }
        return object;*/
        return null;
    }
}
