package com.jxa.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
* 自定义的异常类，继承RuntimeException类
* 实现一些构造.
* 制定返回的状态码，添加@ResponseStatus(HttpStatus.NOT_FOUND)注解,
* 这个注解会把此exception作为资源找不到的状态返回404页面
*
* */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
