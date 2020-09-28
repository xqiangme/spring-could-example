package com.example.ribbon.controller;

import com.example.ribbon.api.HelloFeignServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 服务消费者-测试 controller
 *
 * @author 程序员小强
 */
@RestController
public class TestController {
  private static final Logger log = LoggerFactory.getLogger(TestController.class);

  @Value("${spring.application.name}")
  private String applicationName;

  @Autowired
  private HelloFeignServiceApi helloFeignServiceApi;

  @RequestMapping("/hello")
  public Map<String, Object> hello() {
    log.info(" [ Feign 消费者 {} ]  >> hello method start ", applicationName);
    Map<String, Object> resultMap = helloFeignServiceApi.hello(applicationName);
    log.info(" [ Feign 消费者 {} ]  >> hello method end , resultMap: {}", applicationName, resultMap);
    return resultMap;
  }

  @RequestMapping("/api-feign/hello")
  public Map<String, Object> apiFeignHello() {
    log.info(" [ Feign 消费者 {} ]  >> api-feign-hello method start ", applicationName);
    Map<String, Object> resultMap = helloFeignServiceApi.hello(applicationName);
    log.info(" [ Feign 消费者 {} ]  >> api-feign-hello method end , resultMap: {}", applicationName, resultMap);
    return resultMap;
  }

}
