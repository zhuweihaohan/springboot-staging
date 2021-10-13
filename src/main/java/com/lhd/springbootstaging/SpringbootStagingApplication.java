package com.lhd.springbootstaging;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lhd.springbootstaging.mapper")
public class SpringbootStagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStagingApplication.class, args);
    }

}
