package com.example.ribbon.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 程序员小强
 * @date 2020-09-21 21:37
 */
@Service
public class HelloServiceApi {

  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "hystrixError")
  public Map<String, Object> hello(String consumerApplicationName) {
    return restTemplate.getForObject("http://server-provider/hello?consumerApplicationName=" + consumerApplicationName, Map.class);
  }

  public Map<String, Object> hystrixError(String consumerApplicationName) {
    Map<String, Object> resultMap = new LinkedHashMap<>();
    resultMap.put("code", 500);
    resultMap.put("messages", "网络繁忙，请稍后再试");
    resultMap.put("consumerApplicationName", consumerApplicationName);
    return resultMap;
  }
}
