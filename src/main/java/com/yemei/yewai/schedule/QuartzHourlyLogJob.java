package com.yemei.yewai.schedule;

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
   public void execute(JobExecutionContext context) throws JobExecutionException {
       System.out.println("Hourly log");

       // 打印日志信息
       LOGGER.info("当前时间：" + sdf.format(new Date()));
   }
}
