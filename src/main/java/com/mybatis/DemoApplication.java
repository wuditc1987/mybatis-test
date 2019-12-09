package com.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.Duration;
import java.time.Instant;

/**
 * @author wudi
 */
@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = "com.mybatis.*.mapper")
public class DemoApplication {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        Instant inst1 = Instant.now();
        SpringApplication.run(DemoApplication.class, args);
        log.info("====== 启动成功，耗时:{}秒 ====== ",Duration.between(inst1,Instant.now()).getSeconds());
    }

}
