package com.lyl.test_utils.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class MyRestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final Map globalExceptionHandler(Throwable throwable){
        log.error(throwable.getMessage());
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("status",false);
        map.put("msg", throwable.getMessage());
        return map;
    }
}
