//package com.example.ribbon.hystrix;
//
//import com.example.ribbon.api.HelloFeignServiceApi;
//import org.springframework.stereotype.Component;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * Hystric 组件
// *
// * @author 程序员小强
// */
//@Component
//public class HelloFeignServiceHystric implements HelloFeignServiceApi {
//
//  /**
//   * 断路 hello方法
//   *
//   * @param consumerApplicationName
//   */
//  @Override
//  public Map<String, Object> hello(String consumerApplicationName) {
//    Map<String, Object> resultMap = new LinkedHashMap<>();
//    resultMap.put("code", 500);
//    resultMap.put("messages", "网络繁忙，请稍后再试");
//    resultMap.put("consumerApplicationName", consumerApplicationName);
//    return resultMap;
//  }
//
//}
