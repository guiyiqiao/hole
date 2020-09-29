package com.wizzstudio.hole.config;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 桂乙侨
 * @Date 2020/9/9 15:31
 * @Version 1.0
 */
@Configuration
public class MapConfig {
    @Bean
    public ConcurrentHashMap<Integer,Integer> hugMap(){
        ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap<>();
        return map;
    }

    @Bean
    public ConcurrentHashMap<Integer,Integer> thankMap(){
        return new ConcurrentHashMap<Integer, Integer>();
    }
}
