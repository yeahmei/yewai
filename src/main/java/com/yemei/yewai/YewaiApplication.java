package com.yemei.yewai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YewaiApplication {

    public static void main(String[] args){
        SpringApplication.run(YewaiApplication.class, args);
    }
}
/* 仅提交此增加注释文件 */