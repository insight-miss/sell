package com.imooc.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    private final Logger log = LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void test() {
        String name = "ts";
        String password = "123456";
        log.info("name:{},password:{}",name,password);
        log.info("log...");
        log.error("error...");
    }
}
