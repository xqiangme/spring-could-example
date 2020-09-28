package com.example.ribbon.controller;

import com.example.ribbon.api.HelloServiceApi;
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
  private HelloServiceApi helloServiceApi;

  @RequestMapping("/hello")
  public Map<String, Object> hello() {
    log.info(" [ Ribbon 消费者 {} ]  >> hello method start ", applicationName);
    Map<String, Object> resultMap = helloServiceApi.hello(applicationName);
    log.info(" [ Ribbon 消费者 {} ]  >> hello method end , resultMap: {}", applicationName, resultMap);
    return resultMap;
  }

  @RequestMapping("/api-ribbon/hello")
  public Map<String, Object> apiRibbonHello() {
    log.info(" [ Ribbon 消费者 {} ]  >> api-ribbon-hello method start ", applicationName);
    Map<String, Object> resultMap = helloServiceApi.hello(applicationName);
    log.info(" [ Ribbon 消费者 {} ]  >> api-ribbon-hello method end , resultMap: {}", applicationName, resultMap);
    return resultMap;
  }

}
