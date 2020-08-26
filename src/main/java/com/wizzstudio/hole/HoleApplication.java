package com.wizzstudio.hole;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wizzstudio.hole.mapper")
public class HoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoleApplication.class, args);
    }

}
