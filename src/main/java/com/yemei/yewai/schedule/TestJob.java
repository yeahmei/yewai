package com.yemei.yewai.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: Quartz自动任务demo（通过yml配置方式）
 * Author: zjm
 * Date: 2023/7/1 16:51
 */

@Service
@Async
public class TestJob extends QuartzJobBean {
    // 定义日志记录器对象
    public static final Logger LOGGER = LogManager.getLogger(TestJob.class);

    // 定义日期格式
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext context) {
        // 打印日志信息
        LOGGER.info("QuartzHourlyLogTask当前时间：" + sdf.format(new Date()));
    }
}