package com.example.provider.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 异常过滤器
 *
 * @author 程序员小强
 */
@Component
public class ErrorFilter extends ZuulFilter {

  private static final Logger log = LoggerFactory.getLogger(ErrorFilter.class);

  @Override
  public String filterType() {
    return "error";
  }

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    log.error("[ Zuul ErrorFilter ] start ");
    RequestContext ctx = RequestContext.getCurrentContext();
    Throwable throwable = ctx.getThrowable();
    log.error("[ Zuul ErrorFilter ] stack ", throwable.getCause());

    return null;
  }
}
