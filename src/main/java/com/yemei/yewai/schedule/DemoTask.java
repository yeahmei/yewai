package com.yemei.yewai.schedule;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: Spring自动任务demo
 * Author: minghua.xie
 * Date: 2023/6/07/05
 */
@Component
@Slf4j
public class DemoTask{


    /**
     * cron的配置在yml，统一格式 scheduling.任务类型.任务名称.cron
     */
    @Scheduled(cron = "${scheduling.demo.task1.cron}")
    public void task1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("cronTask 当前时间：" + sdf.format(new Date()));
    }


}
