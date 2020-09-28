package com.example.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon 方式 服务消费者-实例
 *
 * @author 程序员小强
 */
@EnableHystrix
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class RibbonApplication {

  public static void main(String[] args) {
    SpringApplication.run(RibbonApplication.class, args);
  }

  /**
   * 初始化 Rest模板 bean
   *
   * @LoadBalanced 注解表明这个restRemplate开启负载均衡的功能
   */
  @Bean
  @LoadBalanced
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

}

