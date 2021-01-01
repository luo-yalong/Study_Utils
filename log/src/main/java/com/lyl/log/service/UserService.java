package com.lyl.log.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Async
    public void query2() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.println("query2==> " + i);
        }
        System.out.println("query2执行完成");
    }

    @Async
    public void query3() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.println("query3--> "+i);
        }
        System.out.println("query3执行完成");
    }

    @Async
    public void query4(){
        System.out.println("query4执行完成");
    }
}