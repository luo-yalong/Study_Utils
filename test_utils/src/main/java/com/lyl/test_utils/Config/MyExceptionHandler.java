package com.lyl.test_utils.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice(annotations = {Controller.class})
public class MyExceptionHandler {

    private static final String DEFAULT_ERROR_VIEW = "error/error";

    @ExceptionHandler(Exception.class)
    public final ModelAndView globalException(Throwable throwable, HttpServletRequest request){
        log.error(throwable.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        //将异常信息设置进model中
        modelAndView.addObject("msg", throwable);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }
}
