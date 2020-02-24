package com.jxa.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    /*重写预处理方法
    * 在方法调用之前进行预处理
    * 检查session中是否有user属性
    * --request.getSession().getAttribute("user") == null
    * 如果有就放行
    * return true;
    * 如果没有就重定向到登录页面
    * --response.sendRedirect("/admin");
    *
    * */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        /*如果session中没有user属性，就重定向到/admin*/
        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/admin");
            return false;
        }
/*        return super.preHandle(request, response, handler);*/
        return true;
    }

}
