package com.jxa.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    /*
    * 处理异常信息
    * HttpServletRequest request获取请求路径，从而找出出错的方法
    * ModelAndView mv = new ModelAndView();定位到错误页面
    * Exception e,传递异常
    * @ExceptionHandler(Exception.class)此注解表示，此方法是做异常处理的
    *
    * */
    /*获取logger对象，记录错误日志*/
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        /*记录错误信息
        * logger.error("Request URL : {}, Exception : {}",request.getRequestURL(),e);
        * 记录请求信息和错误日志信息
        * */
        logger.error("Request URL : {}, Exception : {}",request.getRequestURL(),e);
        /**/
        if ( AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null ){
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("ErrorURL",request.getRequestURL());//记录发生错误的请求路径
        mv.addObject("exception",e);//记录错误信息
        mv.setViewName("error/error");//跳转到制定页面--error.html
        return mv;
    }
}
