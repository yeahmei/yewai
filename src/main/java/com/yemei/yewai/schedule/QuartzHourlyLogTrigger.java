package com.yemei.yewai.schedule;

import org.quartz.CronScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class QuartzHourlyLogTrigger {

       public static Trigger getTrigger() {
           return TriggerBuilder.newTrigger()
                   .withIdentity("quartzHourlyLogTrigger", "quartzHourlyLogGroup")
                   .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * * * ?"))
//                   .startNow()
//                   .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                           .withIntervalInSeconds(1)
//                           .repeatForever())
                   .build();
       }
   }
