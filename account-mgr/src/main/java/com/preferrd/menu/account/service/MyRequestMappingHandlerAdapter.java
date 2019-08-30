package com.preferrd.menu.account.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class MyRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {
    static {
        System.out.println("RequestMappingHandlerAdapter init");
    }

    @Override
    public void setResponseBodyAdvice(List<ResponseBodyAdvice<?>> responseBodyAdvice) {
        super.setResponseBodyAdvice(responseBodyAdvice);
    }

    @Override
    public void setRequestBodyAdvice(List<RequestBodyAdvice> requestBodyAdvice) {
        super.setRequestBodyAdvice(requestBodyAdvice);
    }
}
