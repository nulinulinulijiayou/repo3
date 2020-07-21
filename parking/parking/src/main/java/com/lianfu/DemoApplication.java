package com.lianfu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lianfu.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.lianfu.DemoApplication.class, args);
    }

}
