package com.yemei.yewai.schedule;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Description: Quartz自动任务demo（通过yml配置方式）
 * Author: zjm
 * Date: 2023/7/1 16:51
 */

@Configuration
public class QuartzConfig {

    @Autowired
    private Environment environment;

    @Bean
    public JobDetail jobDetail_1() {
        return JobBuilder.newJob(TestJob.class)    //被执行的类
                .withIdentity("jobDetail_1")    
                .storeDurably().build();
    }

    @Bean
    public Trigger myTrigger() {
        String cron = environment.getProperty("spring.quartz.job.cron");
        return TriggerBuilder.newTrigger()
                .forJob("jobDetail_1")        
                .withIdentity("myTrigger")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))    //任务调度cron表达式，这里以字符串传递，后台可动态配置
                .build();
    }
}