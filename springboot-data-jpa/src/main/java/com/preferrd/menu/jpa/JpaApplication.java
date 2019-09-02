package com.preferrd.menu.jpa;

import com.preferrd.menu.jpa.repository.MyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaApplication {
    @Autowired
    private MyJpaRepository myJpaRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println(myJpaRepository.findAll());
        };
    }
}
