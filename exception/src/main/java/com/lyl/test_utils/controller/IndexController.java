package com.lyl.test_utils.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class IndexController {

    @GetMapping("/index")
    public Map index(){
        Map<String,Object> map = new LinkedHashMap<>();
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
}
