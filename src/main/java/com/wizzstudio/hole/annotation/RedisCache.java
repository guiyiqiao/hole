package com.wizzstudio.hole.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Redis aop缓存注解
 * @Author 桂乙侨
 * @Date 2020/9/3 21:30
 * @Version 1.0
 */
@Deprecated
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache{
    String prefix() default "hole";
    //缓存过期时间以秒计 默认1小时 60*60*1 = 3600
    int expire() default 3600;
}
