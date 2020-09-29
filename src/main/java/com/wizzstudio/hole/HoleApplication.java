package com.wizzstudio.hole;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableCaching
//@EnableScheduling
@MapperScan("com.wizzstudio.hole.mapper")
public class HoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoleApplication.class, args);
    }

}
