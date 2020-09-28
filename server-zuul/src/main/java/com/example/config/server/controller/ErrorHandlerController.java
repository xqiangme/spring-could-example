package com.example.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 异常后统一返回 controller
 *
 * @author 程序员小强
 */
@RestController
public class ErrorHandlerController implements ErrorController {

  private static final Logger log = LoggerFactory.getLogger(ErrorHandlerController.class);

  @Autowired
  private ErrorAttributes errorAttributes;

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @RequestMapping(value = "/error", produces = "application/json; charset=utf-8")
  public Map<String, Object> error(HttpServletRequest request) {
    Map<String, Object> errorAttributes = getErrorAttributes(request);
    String message = (String) errorAttributes.get("message");
    //堆栈信息
    String trace = (String) errorAttributes.get("trace");
    //异常
    Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

    Map<String, Object> resultMap = new LinkedHashMap<>();
    resultMap.put("code", 500);
    resultMap.put("messages", "网络繁忙，请稍后再试 ," + exception.getMessage());

    return resultMap;
  }

  private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
    return errorAttributes.getErrorAttributes(new ServletWebRequest(request), true);
  }

}
