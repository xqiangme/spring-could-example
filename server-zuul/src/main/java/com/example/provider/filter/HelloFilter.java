package com.example.provider.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 前置过滤器-测试过滤器之间传值
 *
 * @author 程序员小强
 */
@Component
public class HelloFilter extends ZuulFilter {
  private static final Logger log = LoggerFactory.getLogger(HelloFilter.class);

  /**
   * 定义filter的类型，有pre、route、post、error四种
   */
  @Override
  public String filterType() {
    return "pre";
  }

  /**
   * 定义filter的顺序，数字越小表示顺序越高，越先执行
   */
  @Override
  public int filterOrder() {
    return 1;
  }

  /**
   * 可以自定义-逻辑判断，是否要过滤
   */
  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    //请求上下文
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    //测试过滤器之间传值
    Object helloWorld = ctx.get("helloWorld");

    String host = request.getRemoteHost();
    String method = request.getMethod();
    String url = request.getRequestURI();
    log.info("[ Zuul HelloFilter ] start >> host:{},requestMethod:{},url:{},helloWorld:{}", host, method, url, helloWorld);

    log.info("[ Zuul HelloFilter ] end >> host:{},requestMethod:{},url:{}", host, method, url);
    return null;
  }

}
