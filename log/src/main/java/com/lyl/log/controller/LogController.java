package com.lyl.log.controller;

import com.lyl.log.config.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @GetMapping("/noWatchErr")
    public Map noWatchErr(Integer num){
        if(num.equals("abc")){
            System.out.println(num);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("state", true);
        map.put("msg", "正常的输出");
        return map;

    }

    @GetMapping("/index")
    public Map log(){
        Map<String,Object> map = new HashMap<>();
        log.info("你好");
        log.error("错误信息");
        map.put("state", true);
        map.put("msg", "正常的输出");
        return map;
    }



    /**
     * 手抛异常
     */
    @RequestMapping("/err")
    public Map err(){
        throw new RuntimeException("抛出一个异常");
    }

    /**
     * 抛出的是runtimeException不可察异常
     */
    @RequestMapping("/matcherr")
    public Map matcherr(){
        Map<String,Object> map = new HashMap<>();
        map.put("state", true);
        map.put("msg", "正常的输出");
        int i=1/0;
        return map;
    }

    @GetMapping("/myError")
    public Map MyError() throws MyException {

        throw new MyException();

    }
}
