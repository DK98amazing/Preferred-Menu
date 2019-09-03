package com.preferrd.menu.jpa;

import com.preferrd.menu.jpa.entity.Customer;
import com.preferrd.menu.jpa.repository.MyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.preferrd.menu.jpa.*", "com.preferrd.menu.security.config"})
public class JpaApplication {
    @Autowired
    private MyJpaRepository myJpaRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            Customer customer = new Customer("first", "last");
            myJpaRepository.save(customer);
            System.out.println(myJpaRepository.findAll());
            myJpaRepository.deleteAll();
            System.out.println(myJpaRepository.count());
        };
    }
}
