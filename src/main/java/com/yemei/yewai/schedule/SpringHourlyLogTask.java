package com.yemei.yewai.schedule;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: Spring自动任务demo
 * Author: zjm
 * Date: 2023/6/22 16:51
 */
@Component
public class SpringHourlyLogTask implements CommandLineRunner {

    // 定义日志记录器对象
    public static final Logger LOGGER = LogManager.getLogger(SpringHourlyLogTask.class);

    // 定义日期格式
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    @Scheduled(cron = "0/1 * * * * ?")
    @Scheduled(cron = "0 0 * * * ?")
    public void scheduledTask() {
        // 打印日志信息
        LOGGER.info("SpringHourlyLogTask当前时间：" + sdf.format(new Date()));
    }

    @Override
    public void run(String... args) throws Exception {
        // 打印日志信息
        scheduledTask();
    }
}
