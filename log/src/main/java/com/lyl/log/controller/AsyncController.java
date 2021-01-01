package com.lyl.log.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    @GetMapping("/async")
    public String testAsync() throws InterruptedException {
        print();
        print2();
        return "你好";
    }

    @Async
    public void print() throws InterruptedException {
        System.out.println("测试一");
        Thread.sleep(1000);
        System.out.println("测试一终于睡醒了");
    }

    @Async
    public void print2(){
        System.out.println("测试二");
    }
}
