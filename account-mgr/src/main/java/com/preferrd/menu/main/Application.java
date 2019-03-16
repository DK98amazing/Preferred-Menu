package com.preferrd.menu.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;


@SpringBootApplication
//@MapperScan(basePackages = "tk.mybatis.springboot.mapper")
@RestController @RequestMapping(value = "/rest")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
//
//    @GetMapping("/home1")
//    ModelAndView home1() {
//        ModelAndView mv = new ModelAndView();
//        // request url不变
//        mv.setViewName("forward:/rest/cities");
//        return mv;
//    }
//
//    @GetMapping("/home2")
//    ModelAndView home2() {
//        ModelAndView mv = new ModelAndView();
//        // request url改变
//        mv.setViewName("redirect:/rest/cities");
//        return mv;
//    }
//
//    @GetMapping("/cities")
//    String cities() {
//        return "cities";
//    }
}
