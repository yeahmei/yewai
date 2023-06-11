package com.yemei.yewai;

import com.yemei.yewai.schedule.QuartzHourlyLogJob;
import com.yemei.yewai.schedule.QuartzHourlyLogTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YewaiApplication {

    public static void main(String[] args) throws SchedulerException  {
        SpringApplication.run(YewaiApplication.class, args);
        startJob();
    }

    private static void startJob() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        sched.start();

        JobDetail job = JobBuilder.newJob(QuartzHourlyLogJob.class).withIdentity("quartzHourlyLogTrigger", "quartzHourlyLogGroup").build();
        Trigger trigger = QuartzHourlyLogTrigger.getTrigger();
        sched.scheduleJob(job, trigger);
    }

}
