package com.recycle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.recycle.mapper")
public class RecycleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecycleApplication.class,args);
    }
}
