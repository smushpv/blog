package com.jxa.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/*
* @Aspectz切面类
* @Component开启扫描
* */
@Aspect
@Component
public class LogAspect {
    /*定义一个切面
    * 添加@Pointcut(),此注解表示，此方法是一个切面
    * 拦截web包下的所有的类的所有的方法
    * */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //切面
    @Pointcut("execution(* com.jxa.blog.web.*.*(..))")
    public void log(){}

    //这个方法会在切面之前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        /*通过JoinPoint joinPoint来获取访问方法的名称
        * joinPoint.getSignature().getDeclaringTypeName()//获取调用的类
        * joinPoint.getSignature().getName()调用的方法的名称
        * */
        String classMethod ="class:" + joinPoint.getSignature().getDeclaringTypeName() + ";method:" + joinPoint.getSignature().getName();
        /*joinPoint.getArgs()--获取调用方法的名称*/
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url,ip,classMethod,args );

        logger.info("Request : {}",requestLog);
    }

    //再切面之后执行
    @After("log()")
    public void doAfter(){
        logger.info("============doAfter========");
    }

    //返回的结果
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result : {}",result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String className;
        private Object[] args;

        public RequestLog(String url, String ip, String className, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.className = className;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", className='" + className + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
