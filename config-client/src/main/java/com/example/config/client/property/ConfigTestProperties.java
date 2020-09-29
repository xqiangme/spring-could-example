package com.example.config.client.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 配置类读取配置测试
 *
 * @author 程序员小强
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "config.test")
public class ConfigTestProperties {

  private String username;

  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
