package com.cuixiaobin;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public class T_10logback {
    public static final Logger  LOGGER= (Logger) LoggerFactory.getLogger("log");

    public static void main(String[] args) {

        try{
            LOGGER.debug("debug");
            LOGGER.info("info");
            LOGGER.warn("warn");
            LOGGER.error("error");
            int i=10/0;
        }catch(Exception e){
            LOGGER.error("error");
        }

    }
}


class LogBackTest {
    // 创建一个Logger日志对象
    public static final Logger LOGGER = LoggerFactory.getLogger("LogBackTest");

    public static void main(String[] args) {
        //while (true) {
        try {
            LOGGER.info("chu法方法开始执行~~~");
            chu(10, 0);
            LOGGER.info("chu法方法执行成功~~~");
        } catch (Exception e) {
            LOGGER.error("chu法方法执行失败了，出现了bug~~~");
        }
        //}
    }

    public static void chu(int a, int b){
        LOGGER.debug("参数a:" + a);
        LOGGER.debug("参数b:" + b);
        int c = a / b;
        LOGGER.info("结果是：" + c);
    }
}