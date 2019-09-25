package com.example.log4j2;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2(topic = "OrderServiceTopicTest")
public class OrderService {

    public void testOrderLog(int i) {
        log.info("这是订单模块打印的info信息2，通过这段文件来增长info信息的长度，以贴近实际使用的情况。。。");
        log.info("这是订单模块打印的info信息1，通过这段文件来增长info信息的长度，以贴近实际使用的情况。。。");
        log.debug("这是订单模块打印的debug信息1，通过这段文件来增长info信息的长度，以贴近实际使用的情况。。。");
        log.debug("这是订单模块打印的debug信息2，通过这段文件来增长info信息的长度，以贴近实际使用的情况。。。");
        if (i % 100 == 0) {
            log.error("这是订单模块打印的error信息 {}", new RuntimeException("运行时异常测试"));
        }
    }

}
