package com.preferrd.menu.account;

//import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan({ "com.preferrd.menu.account.*", "com.preferrd.menu.database.*", "com.preferrd.menu.email.*",
                   "com.preferrd.menu.redis", "com.preferrd.menu.aop.log.*" })
@MapperScan("com.preferrd.menu.database.dao")
@ImportResource(value = { "classpath:dubbo-provider.xml" })
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                //System.out.println(beanName);
            }

        };
    }

}
