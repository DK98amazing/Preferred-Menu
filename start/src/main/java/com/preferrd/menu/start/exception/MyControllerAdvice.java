package com.preferrd.menu.start.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.prefrred.exception.SelectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Component
@ControllerAdvice
public class MyControllerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(MyControllerAdvice.class);

    @ExceptionHandler(value = SelectException.class)
    public JSON handleException(SelectException e, Model model) {
        LOG.error("Exception occur: " + e.getMessage());
        model.addAttribute("cause", e.getCause());
        model.addAttribute("message", e.getMessage());
        return (JSON) JSONObject.toJSON(model);
    }

}
