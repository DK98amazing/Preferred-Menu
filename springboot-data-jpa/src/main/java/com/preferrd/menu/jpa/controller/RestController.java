package com.preferrd.menu.jpa.controller;

import com.preferrd.menu.jpa.entity.Customer;
import com.preferrd.menu.jpa.repository.MyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/test")
public class RestController {
    @Autowired
    private MyJpaRepository myJpaRepository;

    @GetMapping("/test")
    public String test() {
        System.err.println("test");
        System.err.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        System.err.println(((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getSessionId());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            System.err.println(((UserDetails) principal).getPassword());
        } else {
            System.out.println(principal.toString());
        }
        System.err.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
        Customer customer = new Customer("first", "last");
        myJpaRepository.save(customer);
        System.out.println(myJpaRepository.findAll());
        myJpaRepository.deleteAll();
        System.out.println(myJpaRepository.count());
        return "success";
    }

    @GetMapping("/test2")
    public String test2() {
        System.err.println("test2");
        Customer customer = new Customer("first", "last");
        myJpaRepository.save(customer);
        System.out.println(myJpaRepository.findAll());
        myJpaRepository.deleteAll();
        System.out.println(myJpaRepository.count());
        return "success";
    }
}
