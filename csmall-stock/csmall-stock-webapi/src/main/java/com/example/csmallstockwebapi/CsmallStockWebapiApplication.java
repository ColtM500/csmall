package com.example.csmallstockwebapi;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 如果当前项目是Dubbo调用中的生产者 必须添加@EnableDubbo注解
// 添加之后，在服务启动时，当前项目的提供的所有服务，才能正常被消费者消费
@EnableDubbo
@SpringBootApplication
public class CsmallStockWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsmallStockWebapiApplication.class, args);
    }

}
