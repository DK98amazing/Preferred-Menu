package com.preferrd.menu.account;

//import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

//@EnableDubbo
@SpringBootApplication
@ComponentScan({ "com.preferrd.menu.account", "com.preferrd.menu.database", "com.preferrd.menu.email" })
@MapperScan("com.preferrd.menu.database.dao")
@ImportResource(value = { "classpath:dubbo-provider.xml" })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
