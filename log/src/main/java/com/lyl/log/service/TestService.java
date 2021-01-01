package com.lyl.log.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestService {

    private String str ;

    public Map<String,Object> getMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("state", false);
        map.put("msg", "测试消息");
        return map;
    }

    public Map<String,Object> getMap1(){
        Map<String,Object> map = new HashMap<>();
        map.put("state", false);
        map.put("msg", "测试消息");
        return map;
    }
}
