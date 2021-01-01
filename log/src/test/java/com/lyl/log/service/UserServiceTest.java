package com.lyl.log.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService service;


    public String test2() throws InterruptedException {
        service.query2();
        service.query3();
        service.query4();
        return "测试完成";
    }

    @Test
    public void test3() throws InterruptedException {
        System.out.println(test2());
    }
}