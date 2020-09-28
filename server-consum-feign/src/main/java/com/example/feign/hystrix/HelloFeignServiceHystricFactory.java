package com.example.feign.hystrix;

import com.example.feign.api.HelloFeignServiceApi;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Feign 断路实现
 *
 * @author 程序员小强
 */
@Component
public class HelloFeignServiceHystricFactory implements FallbackFactory<HelloFeignServiceApi> {
  private static final Logger log = LoggerFactory.getLogger(HelloFeignServiceHystricFactory.class);

  @Override
  public HelloFeignServiceApi create(Throwable throwable) {
    String message = throwable.getMessage();
    log.error("[ 服务 hystrix 触发了 ] message:{} ", message, throwable);
    return new HelloFeignServiceApi() {
      @Override
      public Map<String, Object> hello(String consumerApplicationName) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("code", 500);
        resultMap.put("messages", "网络繁忙，请稍后再试");
        resultMap.put("consumerApplicationName", consumerApplicationName);
        return resultMap;
      }
    };
  }
}
