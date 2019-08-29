package com.preferrd.menu.start;

//import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

import com.preferrd.menu.redis.ConfigProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
@EnableConfigurationProperties(ConfigProperties.class)
public class Application {

    @Autowired
    private ApplicationContext applicationCtx;

    @Value("${test.name}")
    private String str;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setAddCommandLineProperties(false);
        springApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                if (beanName.equalsIgnoreCase("ConfigProperties")) {
                    System.out.println(beanName);
                }
            }

            System.err.println("********" + ((ConfigProperties)applicationCtx.getBean("configProperties")).getHostName());
            System.err.println("********" + str);
        };
    }

}
