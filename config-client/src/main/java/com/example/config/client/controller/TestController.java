package com.example.config.client.controller;

import com.example.config.client.property.ConfigTestProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 配置中心客户端测试
 *
 * @author 程序员小强
 */
@RestController
public class TestController {
  private static final Logger log = LoggerFactory.getLogger(TestController.class);

  @Value("${server.port}")
  private String port;

  @Value("${env}")
  private String env;

  @Value("${hello}")
  private String hello;

  @Value("${version}")
  private String version;

  @Autowired
  private ConfigTestProperties configTestProperties;

  @RequestMapping("/hello")
  public Map<String, Object> hello() {
    Map<String, Object> resultMap = new LinkedHashMap<>(4);
    resultMap.put("port", port);
    resultMap.put("hello", hello);
    resultMap.put("env", env);
    resultMap.put("version", version);
    resultMap.put("username", configTestProperties.getUsername());
    resultMap.put("password", configTestProperties.getPassword());
    return resultMap;
  }
}
