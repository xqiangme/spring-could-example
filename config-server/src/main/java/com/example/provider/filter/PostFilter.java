package com.example.provider.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * post 过滤器
 *
 * @author 程序员小强
 */
@Component
public class PostFilter extends ZuulFilter {
  private static final Logger log = LoggerFactory.getLogger(ErrorFilter.class);

  @Override
  public String filterType() {
    //后置过滤器
    return "post";
  }

  @Override
  public int filterOrder() {
    //优先级，数字越大，优先级越低
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    //是否执行该过滤器，true代表需要过滤
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    log.info("[ Zuul PostFilter ] start ");

    //模拟异常
    //int i = 1 / 0;

    return null;
  }

}
