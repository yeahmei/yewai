package com.yemei.yewai.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdkHourlyLogTask extends TimerTask {

    // 定义日志记录器对象
    public static final Logger LOGGER = LogManager.getLogger(JdkHourlyLogTask.class);

    @Override
    public void run() {
        // 定义指定的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 打印日志的代码
        LOGGER.info("当前时间" + sdf.format(new Date()));

        System.out.println("Hourly log");
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        JdkHourlyLogTask task = new JdkHourlyLogTask();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        timer.schedule(task, calendar.getTime(), 60*60*1000);
    }
}