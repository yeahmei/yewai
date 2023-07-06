package com.yemei.yewai.schedule;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
/**
 * Description: 测试Log4j2打印日志
 * Author: zjm
 * Date: 2023/6/22 16:51
 */
public class Log4j2Test {
    //定义日志记录器对象
    public static final Logger LOGGER = LogManager.getLogger(Log4j2Test.class);

    //快速入门
    @Test
    public void testQuick() throws Exception {
//日志消息输出
        LOGGER.fatal("fatal");
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
    }
}