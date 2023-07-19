package com.yemei.yewai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication(scanBasePackages = "com.yemei.yewai.schedule")
@EnableScheduling
public class YewaiApplication {

    public static void main(String[] args){
        SpringApplication.run(YewaiApplication.class, args);
    }
}
