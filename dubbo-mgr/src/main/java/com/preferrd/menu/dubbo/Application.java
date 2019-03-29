package com.preferrd.menu.dubbo;

//import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application.
 *
 * @author liguoyao
 */
//@EnableDubbo
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan({ "com.preferrd.menu.dubbo" })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
