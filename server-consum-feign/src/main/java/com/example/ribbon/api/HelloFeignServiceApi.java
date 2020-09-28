package com.example.ribbon.api;

import com.example.ribbon.hystrix.HelloFeignServiceHystricFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 调用生产者测试接口
 *
 * @author 程序员小强
 */
@FeignClient(value = "server-provider",  fallbackFactory = HelloFeignServiceHystricFactory.class)
public interface HelloFeignServiceApi {

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  Map<String, Object> hello(@RequestParam(value = "consumerApplicationName") String consumerApplicationName);
}
