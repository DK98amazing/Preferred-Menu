package com.pmenu.menu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan("com.pmenu.menu.dao.bean")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
