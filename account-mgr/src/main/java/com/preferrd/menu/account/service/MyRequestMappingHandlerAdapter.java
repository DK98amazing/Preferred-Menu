package com.preferrd.menu.account.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

//@Configuration
public class MyRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {
    static {
        System.out.println("RequestMappingHandlerAdapter init");
    }
}
