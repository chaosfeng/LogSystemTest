package com.example.log4j2;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@Log4j2(topic="Test_Log4j2_To_ES")
@RestController
public class Log4j2Application {

    @Autowired
    OrderService orderService;

    @Autowired
    InventoryService inventoryService;

    public static void main(String[] args) {
        SpringApplication.run(Log4j2Application.class, args);
    }

    @RequestMapping(value = "/doTest")
    public void testA(@RequestParam int num) throws Exception {
        System.out.println("start test......");
        System.out.println(new Date());
        System.out.println("threadsNum:" + num);
        // ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < num; i++) {
            // executorService.execute(() -> doLog());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    doLog(num);
                }
            });
            thread.start();
        }
        System.out.println("testing......");
    }

    public void doLog(int num) {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < (10000000/8/num); i++) {
            orderService.testOrderLog(i);
            inventoryService.testInventoryLog(i);
        }
        Long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "完成日志打印，耗时：" + (end - start)/1000 + "seconds");
    }
}
