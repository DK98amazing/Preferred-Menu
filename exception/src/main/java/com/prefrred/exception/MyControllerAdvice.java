package com.prefrred.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.prefrred.exception.myenum.SelectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyControllerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(MyControllerAdvice.class);

    @ExceptionHandler(SelectException.class)
    @ResponseBody
    public JSON handleException(SelectException e, Model model) {
        LOG.error("Exception occur: " + e.getMessage());
        model.addAttribute("cause", e.getCause());
        model.addAttribute("message", e.getMessage());
        return (JSON) JSONObject.toJSON(model);
    }

}
