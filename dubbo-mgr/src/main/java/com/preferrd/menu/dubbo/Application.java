package com.preferrd.menu.dubbo;

//import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * Application.
 *
 * @author liguoyao
 */
//@EnableDubbo
@SpringBootApplication(scanBasePackages = "com.preferrd.menu.dubbo",
                       exclude = { DataSourceAutoConfiguration.class })
@ImportResource("classpath:dubbo-consumer.xml")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
