package com.preferrd.menu.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
//import org.springframework.web.servlet.ModelAndView;


@SpringBootApplication
@ComponentScan({"com.preferrd.menu.account", "com.preferrd.menu.database"})
@MapperScan("com.preferrd.menu.database.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
