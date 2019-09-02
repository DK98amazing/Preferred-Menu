package com.preferrd.menu.jpa.controller;

import com.preferrd.menu.jpa.entity.Customer;
import com.preferrd.menu.jpa.repository.MyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/test")
public class RestController {
    @Autowired
    private MyJpaRepository myJpaRepository;

    @GetMapping("/test")
    public void test() {
        Customer customer = new Customer("first", "last");
        myJpaRepository.save(customer);
        System.out.println(myJpaRepository.findAll());
        myJpaRepository.deleteAll();
        System.out.println(myJpaRepository.count());
    }
}
