package com.example.provider.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 定义 zuul token参数校验-过滤器
 *
 * @author 程序员小强
 */
@Component
public class ZuulCheckTokenFilter extends ZuulFilter {
  private static final Logger log = LoggerFactory.getLogger(ZuulCheckTokenFilter.class);

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
    return 0;
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
    ctx.set("helloWorld", "hello zuul ");

    String host = request.getRemoteHost();
    String method = request.getMethod();
    String uri = request.getRequestURI();
    log.info("[ Zuul CheckTokenFilter ] start >> host:{},requestMethod:{},url:{}", host, method, uri);

    //TODO 自定义功能 示例校验token 参数
    Object token = request.getParameter("token");
    if (!ObjectUtils.isEmpty(token)) {
      // TODO 校验token...
      log.info("[ Zuul CheckTokenFilter ] end >> host:{},requestMethod:{},url:{},token:{}", host, method, uri, token);
      return null;
    }

    log.error("[ Zuul CheckTokenFilter ] token is empty >> host:{},method:{},url:{}", host, method, uri);
    //失败返回
    ctx.setSendZuulResponse(false);
    ctx.setResponseStatusCode(401);

    try {
      //返回信息
      Map<String, Object> resultMap = new LinkedHashMap<>(4);
      resultMap.put("code", 401);
      resultMap.put("messages", "token is empty");
      //写出异常内容
      ctx.getResponse().getWriter().write(JSON.toJSONString(resultMap));
      ctx.getResponse().setContentType("application/json; charset=utf-8");
    } catch (Exception e) {
      log.error("[ Zuul CheckTokenFilter ] exception >> host:{},method:{},url:{}", host, method, uri, e);
    }
    return null;
  }
}
