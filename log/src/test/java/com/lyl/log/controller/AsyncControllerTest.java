package com.lyl.log.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AsyncControllerTest {

    private AsyncController asyncController = new AsyncController();
    @Test
    void testAsync() throws InterruptedException {
        asyncController.testAsync();


    }
}