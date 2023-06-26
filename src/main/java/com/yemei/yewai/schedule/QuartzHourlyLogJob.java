package com.yemei.yewai.schedule;

/**
 * Description: Quartz自动任务demo
 * Author: zjm
 * Date: 2023/6/22 16:51
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzHourlyLogJob implements Job {
    // 定义日志记录器对象
    public static final Logger LOGGER = LogManager.getLogger(QuartzHourlyLogJob.class);
    // 定义日期格式
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 打印日志信息
        LOGGER.info("QuartzHourlyLogJob当前时间：" + sdf.format(new Date()));
    }
}